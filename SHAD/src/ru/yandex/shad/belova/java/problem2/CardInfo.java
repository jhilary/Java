package ru.yandex.shad.belova.java.problem2;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 1:51 AM
 * To change this template use File | Settings | File Templates.
 */


public class CardInfo {

    private boolean expired;
    private Date validFrom;
    private Date validTo;
    private int numberOfTripsLeft;
    private int balance;

    public boolean getExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public int getNumberOfTripsLeft() {
        return numberOfTripsLeft;
    }

    public void setNumberOfTripsLeft(int numberOfTripsLeft) {
        this.numberOfTripsLeft = numberOfTripsLeft;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}

