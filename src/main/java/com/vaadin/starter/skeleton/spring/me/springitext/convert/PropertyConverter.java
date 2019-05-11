package com.vaadin.starter.skeleton.spring.me.springitext.convert;

public interface PropertyConverter<PROPERTY>{

    String convert(PROPERTY property);
}
