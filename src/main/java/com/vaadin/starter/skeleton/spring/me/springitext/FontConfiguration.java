package com.vaadin.starter.skeleton.spring.me.springitext;

import com.itextpdf.text.BaseColor;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Scope;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FontConfiguration {

    String fontName();

    String encoding();

    int style();

    float size();

    Color color();

}
