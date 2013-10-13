package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:27 PM
 * To change this template use File | Settings | File Templates.
 */


interface Card {

    enum OwnerType {
        Pupil, Student, Regular
    }

    enum UsageType {
        Period, Trips, Accumulative
    }

    String getID();
    OwnerType getOwnerType();
    UsageType getUsageType();

    CardInfo getCardInfo();
    void updateCardInfo(CardInfo cardInfo);

    boolean validate(int amount);
    void pay(int amount);

}
