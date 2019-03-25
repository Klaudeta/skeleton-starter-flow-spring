package com.vaadin.starter.skeleton.spring;

public class Phone {
    String number;

    public Phone(String number) {
        this.number = number;
    }

    public Phone() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }
}
