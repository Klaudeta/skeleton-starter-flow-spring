package com.vaadin.starter.skeleton.spring.me.springitext;

import java.util.List;

public interface RowGenerator<ITEM> {

    List<Row> generate(ITEM item);
}
