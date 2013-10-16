package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/12/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Card accumulative  = CardRegistry.getInstance().acquireCard(Card.OwnerType.Regular, new Date(), new Balance(Balance.Type.Money, 100));
        Card dateExpirable = CardRegistry.getInstance().acquireCard(Card.OwnerType.Student, new Date(), new Balance(Balance.Type.Days, 10));
        Card tripCountable = CardRegistry.getInstance().acquireCard(Card.OwnerType.Pupil, new Date(), new Balance(Balance.Type.Days, 5));

        Gateway gt = new Gateway();
        gt.passPassenger(accumulative);
        gt.passPassenger(dateExpirable);
        gt.passPassenger(tripCountable);

        Balance balance = accumulative.getBalance();
        System.out.println(balance.value);
        accumulative.updateBalance(new Balance(Balance.Type.Money, 999));
        balance = accumulative.getBalance();
        System.out.println(balance.value);

        CardRegistry.getInstance().rechargeCardBalance(accumulative.getID(), new Balance(Balance.Type.Money, 5000));
        balance = accumulative.getBalance();
        System.out.println(balance.value);

    }

}
