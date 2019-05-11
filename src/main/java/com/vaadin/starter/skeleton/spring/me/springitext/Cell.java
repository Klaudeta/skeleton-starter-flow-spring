package com.vaadin.starter.skeleton.spring.me.springitext;

import java.util.function.BiFunction;

public class Cell<ITEM> {

    String label;

    ValueGetter<ITEM, ?> getter;

    public Cell(String label, ValueGetter<ITEM, ?> getter) {
        this.label = label;
        this.getter = getter;
    }


    public String getLabel() {
        return label;
    }

    public ValueGetter<ITEM, ?> getGetter() {
        return getter;
    }
}
