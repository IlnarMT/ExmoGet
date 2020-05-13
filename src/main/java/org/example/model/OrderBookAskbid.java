package org.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderBookAskbid {
    public BigDecimal ask_quantity;
    public BigDecimal ask_amount;
    public BigDecimal ask_top;
    public BigDecimal bid_quantity;
    public BigDecimal bid_amount;
    public BigDecimal bid_top;
    public ArrayList<ArrayList<BigDecimal>> ask=new ArrayList<>();
    public ArrayList<ArrayList<BigDecimal>> bid=new ArrayList<>();

    public BigDecimal getAsk_quantity() {
        return ask_quantity;
    }

    public void setAsk_quantity(BigDecimal ask_quantity) {
        this.ask_quantity = ask_quantity;
    }

    public BigDecimal getAsk_amount() {
        return ask_amount;
    }

    public void setAsk_amount(BigDecimal ask_amount) {
        this.ask_amount = ask_amount;
    }

    public BigDecimal getAsk_top() {
        return ask_top;
    }

    public void setAsk_top(BigDecimal ask_top) {
        this.ask_top = ask_top;
    }

    public BigDecimal getBid_quantity() {
        return bid_quantity;
    }

    public void setBid_quantity(BigDecimal bid_quantity) {
        this.bid_quantity = bid_quantity;
    }

    public BigDecimal getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(BigDecimal bid_amount) {
        this.bid_amount = bid_amount;
    }

    public BigDecimal getBid_top() {
        return bid_top;
    }

    public void setBid_top(BigDecimal bid_top) {
        this.bid_top = bid_top;
    }

    public ArrayList<ArrayList<BigDecimal>> getAsk() {
        return ask;
    }

    public void setAsk(ArrayList<ArrayList<BigDecimal>> ask) {
        this.ask = ask;
    }

    public ArrayList<ArrayList<BigDecimal>> getBid() {
        return bid;
    }

    public void setBid(ArrayList<ArrayList<BigDecimal>> bid) {
        this.bid = bid;
    }
}
