package ru.yandex.shad.belova.java.problem2;

/**
 * Created with IntelliJ IDEA.
 * User: olegklymchuk
 * Date: 10/13/13
 * Time: 3:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class TravelCardPassState {

    public enum State {
        Allowed,
        Denied
    }

    public String  gatewayID;
    public String  travelCardID;
    public State   travelCardState;

}
