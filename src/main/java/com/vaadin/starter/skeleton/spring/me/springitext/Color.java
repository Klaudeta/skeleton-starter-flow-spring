package com.vaadin.starter.skeleton.spring.me.springitext;

import com.itextpdf.text.BaseColor;

public enum Color {

    WHITE(BaseColor.WHITE),
    LIGHT_GRAY(BaseColor.LIGHT_GRAY),
    GRAY(BaseColor.GRAY),
    DARK_GRAY(BaseColor.DARK_GRAY),
    BLACK(BaseColor.BLACK),
    RED(BaseColor.RED),

    PINK(BaseColor.PINK),
    ORANGE(BaseColor.ORANGE),
    YELLOW(BaseColor.YELLOW),
    GREEN(BaseColor.GREEN),
    MAGENTA(BaseColor.MAGENTA),
    CYAN(BaseColor.CYAN),
    BLUE(BaseColor.BLUE);

    private BaseColor color;


    Color(BaseColor color) {
        this.color = color;
    }

    public BaseColor getBaseColor() {
        return color;
    }
}
