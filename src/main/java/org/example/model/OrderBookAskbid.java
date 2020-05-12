package org.example.model;

import java.math.BigDecimal;

public class OrderBookAskbid {
    public BigDecimal ask_quantity;
    public BigDecimal ask_amount;
    public BigDecimal ask_top;
    public BigDecimal bid_quantity;
    public BigDecimal bid_amount;
    public BigDecimal bid_top;
    BigDecimal[][] ask = new BigDecimal[100][3];
    BigDecimal[][] bid = new BigDecimal[100][3];
}
