package org.example;

import org.example.model.OrderBook;
import org.example.model.Transact;
import org.jooq.*;
//import org.jooq.codegen.maven.example.tables.Transacts;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import static org.jooq.codegen.maven.example.Tables.*;

public class ConnectDb {

    String userName = "postgres";
    String password = "postgres";
    String url = "jdbc:postgresql://localhost:5432/exmodb";
    Connection conn = DriverManager.getConnection(url, userName, password);
    DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
    ArrayList<Transact> transactsOld;
    Long lastTransactId;
    HashMap<TradePairType,Long> lastTransactIdMap=new HashMap();

    public ConnectDb() throws SQLException {
    }

    // Connection is the only JDBC resource that we need
    // PreparedStatement and ResultSet are handled by jOOQ, internally

    public Long getLastIdInTransactTable(TradePairType tradePairType){
        Long lastIdInTransactTable = null;
        Result<Record1<Long>> result = create.select(TRANSACTS.TRADE_ID).from(TRANSACTS).where(TRANSACTS.TRADE_PAIR.eq(tradePairType.toString())).orderBy(TRANSACTS.TRADE_ID.desc()).limit(1).fetch();
        int k=0;
        for (Record1 r : result) {
            lastIdInTransactTable=r.getValue(TRANSACTS.TRADE_ID);
            //System.out.println(k+". Last ID from TRANSACT table: " + lastIdInTransactTable);
            k++;
        }
        return lastIdInTransactTable;
    }

    public void insertIntoTableTransaction(TradePairType tradePair, ArrayList<Transact> transacts){
        int k=0;
        if (lastTransactIdMap.get(tradePair)==null){
            lastTransactId=this.getLastIdInTransactTable(tradePair);
            lastTransactIdMap.put(tradePair,lastTransactId);
        }
        lastTransactId=lastTransactIdMap.get(tradePair);
        for (Transact i: transacts) {
            //System.out.println(tradePair+" lastTransactId="+lastTransactId+" i.getTrade_id()="+i.getTrade_id());
            if (!i.getTrade_id().equals(lastTransactId)){
                //System.out.println(k+". Метод insertIntoTableTransaction i.getTrade_id()="+i.getTrade_id());
                create.insertInto(TRANSACTS,
                        TRANSACTS.TRADE_ID, TRANSACTS.TRADE_PAIR, TRANSACTS.TYPE, TRANSACTS.QUANTITY,
                        TRANSACTS.PRICE, TRANSACTS.AMOUNT, TRANSACTS.DATE)
                        .values(i.getTrade_id(),tradePair.toString(),i.getType(),i.getQuantity(),i.getPrice(),i.getAmount(),i.getDate())
                        .execute();
            } else break;
            k++;
            if (k==99){
                System.out.println("!!Внимание! Число транзакций за период достиг 100!!!");
            }
        }
        lastTransactId=transacts.get(0).getTrade_id();
        lastTransactIdMap.put(tradePair,lastTransactId);
    }

    public void insertIntoOrderBooks(TradePairType tradePairType, OrderBook orderBook){
        Instant instant=Instant.now();
        Long currentUnixTime=instant.getEpochSecond();
        System.out.println("Current time= "+currentUnixTime);
        for (ArrayList<BigDecimal> i: orderBook.BTC_USD.ask) {
            create.insertInto(ORDER_BOOKS,
                    ORDER_BOOKS.TRADE_PAIR, ORDER_BOOKS.TYPE, ORDER_BOOKS.PRICE, ORDER_BOOKS.QUANTITY, ORDER_BOOKS.AMOUNT, ORDER_BOOKS.DATE)
                    .values( "BTC_USD", "ask",i.get(0),i.get(1),i.get(2),currentUnixTime)
                    .execute();
        }
        for (ArrayList<BigDecimal> i: orderBook.BTC_USD.bid) {
            create.insertInto(ORDER_BOOKS,
                    ORDER_BOOKS.TRADE_PAIR, ORDER_BOOKS.TYPE, ORDER_BOOKS.PRICE, ORDER_BOOKS.QUANTITY, ORDER_BOOKS.AMOUNT, ORDER_BOOKS.DATE)
                    .values( "BTC_USD", "bid",i.get(0),i.get(1),i.get(2),currentUnixTime)
                    .execute();
        }
    }

}
