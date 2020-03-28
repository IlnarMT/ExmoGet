package org.example;

import org.example.model.Transact;
import org.jooq.*;
import org.jooq.codegen.maven.example.tables.Transacts;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public void viewTableAuthor() throws SQLException {
        Result<Record> result = create.select().from(AUTHOR).fetch();
        for (Record r : result) {
            Integer id = r.getValue(AUTHOR.ID);
            String firstName = r.getValue(AUTHOR.FIRST_NAME);
            String lastName = r.getValue(AUTHOR.LAST_NAME);

            System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
        }

/*        create.insertInto(AUTHOR,
                AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .values(4, "Булат", "Зарипов")
                .execute();*/
    }

    public Long getLastIdInTransactTable(TradePairType tradePairType){
        Long lastIdInTransactTable = null;
        Result<Record1<Long>> result = create.select(TRANSACTS.TRADE_ID).from(TRANSACTS).where(TRANSACTS.TRADE_PAIR.eq(tradePairType.toString())).orderBy(TRANSACTS.TRADE_ID.desc()).limit(1).fetch();
        int k=0;
        for (Record1 r : result) {
            lastIdInTransactTable=r.getValue(TRANSACTS.TRADE_ID);
            System.out.println(k+". Last ID from TRANSACT table: " + lastIdInTransactTable);
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
            System.out.println(tradePair+" lastTransactId="+lastTransactId+" i.getTrade_id()="+i.getTrade_id());
            if (!i.getTrade_id().equals(lastTransactId)){
                System.out.println(k+". Метод insertIntoTableTransaction i.getTrade_id()="+i.getTrade_id());
                create.insertInto(TRANSACTS,
                        TRANSACTS.TRADE_ID, TRANSACTS.TRADE_PAIR, TRANSACTS.TYPE, TRANSACTS.QUANTITY,
                        TRANSACTS.PRICE, TRANSACTS.AMOUNT, TRANSACTS.DATE)
                        .values(i.getTrade_id(),tradePair.toString(),i.getType(),i.getQuantity(),i.getPrice(),i.getAmount(),i.getDate())
                        .execute();
                System.out.println();
            } else break;
            k++;
            if (k==99){
                System.out.println("!!Внимание! Число транзакций за период достиг 100!!!");
            }
        }
        lastTransactId=transacts.get(0).getTrade_id();
        lastTransactIdMap.put(tradePair,lastTransactId);
    }

}
