package ru.yandex.shad.belova.java.problem2.Metropolitan;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 1:51 AM
 * To change this template use File | Settings | File Templates.
 */


public class AggregatedCardInfo {

    private boolean expired;
    private DateTime validFrom;
    private DateTime validTo;
    private int numberOfTripsLeft;
    private int balance;
    private int ticketPrice;

    public DateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(DateTime validFrom) {
        this.validFrom = validFrom;
    }

    public DateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(DateTime validTo) {
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

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }
}

