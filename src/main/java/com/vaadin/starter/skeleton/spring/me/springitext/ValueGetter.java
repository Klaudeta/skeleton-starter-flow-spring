package com.vaadin.starter.skeleton.spring.me.springitext;

import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.function.ValueProvider;
import java.io.Serializable;
import java.util.function.Function;

@FunctionalInterface
public interface ValueGetter<SOURCE, TARGET> extends Function<SOURCE, TARGET>, Serializable {
    static <T> ValueGetter<T, T> identity() {
        return (t) -> {
            return t;
        };
    }

    TARGET apply(SOURCE var1);
}
