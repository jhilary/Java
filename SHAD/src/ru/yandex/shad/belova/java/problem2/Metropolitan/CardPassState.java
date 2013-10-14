package ru.yandex.shad.belova.java.problem2.Metropolitan;

public class CardPassState {

    public enum State {
        Allowed,
        Denied
    }

    public String  gatewayID;
    public String  cardID;
    public State   cardState;

}
