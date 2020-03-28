/*
 * This file is generated by jOOQ.
 */
package org.jooq.codegen.maven.example.tables.records;


import java.math.BigDecimal;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.codegen.maven.example.tables.Transacts;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TransactsRecord extends UpdatableRecordImpl<TransactsRecord> implements Record7<Long, String, String, BigDecimal, BigDecimal, BigDecimal, Long> {

    private static final long serialVersionUID = 896368550;

    /**
     * Setter for <code>public.transacts.trade_id</code>.
     */
    public void setTradeId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.transacts.trade_id</code>.
     */
    public Long getTradeId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.transacts.trade_pair</code>.
     */
    public void setTradePair(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.transacts.trade_pair</code>.
     */
    public String getTradePair() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.transacts.type</code>.
     */
    public void setType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.transacts.type</code>.
     */
    public String getType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.transacts.quantity</code>.
     */
    public void setQuantity(BigDecimal value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.transacts.quantity</code>.
     */
    public BigDecimal getQuantity() {
        return (BigDecimal) get(3);
    }

    /**
     * Setter for <code>public.transacts.price</code>.
     */
    public void setPrice(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.transacts.price</code>.
     */
    public BigDecimal getPrice() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>public.transacts.amount</code>.
     */
    public void setAmount(BigDecimal value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.transacts.amount</code>.
     */
    public BigDecimal getAmount() {
        return (BigDecimal) get(5);
    }

    /**
     * Setter for <code>public.transacts.date</code>.
     */
    public void setDate(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.transacts.date</code>.
     */
    public Long getDate() {
        return (Long) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, String, BigDecimal, BigDecimal, BigDecimal, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, String, BigDecimal, BigDecimal, BigDecimal, Long> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Transacts.TRANSACTS.TRADE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Transacts.TRANSACTS.TRADE_PAIR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Transacts.TRANSACTS.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field4() {
        return Transacts.TRANSACTS.QUANTITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field5() {
        return Transacts.TRANSACTS.PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field6() {
        return Transacts.TRANSACTS.AMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field7() {
        return Transacts.TRANSACTS.DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getTradeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getTradePair();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component4() {
        return getQuantity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component5() {
        return getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component6() {
        return getAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component7() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getTradeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTradePair();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value4() {
        return getQuantity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value5() {
        return getPrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value6() {
        return getAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value7() {
        return getDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value1(Long value) {
        setTradeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value2(String value) {
        setTradePair(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value3(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value4(BigDecimal value) {
        setQuantity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value5(BigDecimal value) {
        setPrice(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value6(BigDecimal value) {
        setAmount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord value7(Long value) {
        setDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactsRecord values(Long value1, String value2, String value3, BigDecimal value4, BigDecimal value5, BigDecimal value6, Long value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TransactsRecord
     */
    public TransactsRecord() {
        super(Transacts.TRANSACTS);
    }

    /**
     * Create a detached, initialised TransactsRecord
     */
    public TransactsRecord(Long tradeId, String tradePair, String type, BigDecimal quantity, BigDecimal price, BigDecimal amount, Long date) {
        super(Transacts.TRANSACTS);

        set(0, tradeId);
        set(1, tradePair);
        set(2, type);
        set(3, quantity);
        set(4, price);
        set(5, amount);
        set(6, date);
    }
}