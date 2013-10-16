package ru.yandex.shad.belova.java.problem2;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 1:51 AM
 * To change this template use File | Settings | File Templates.
 */


public class Balance {

    public enum Type {
        Month, Days, Trips, Money
    }

    public Type type;
    public int value;

    public Balance(Type type, int value) {

        this.type = type;
        this.value = value;
    }

}
