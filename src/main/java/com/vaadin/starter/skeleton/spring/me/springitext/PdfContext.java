package com.vaadin.starter.skeleton.spring.me.springitext;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PdfContext {

    @Autowired
    private ApplicationContext applicationContext;

    private static PdfContext instance;

    @PostConstruct
    protected void initialize() {
        instance = this;
    }

    /**
     * @return Current application context of the deployment
     */
    public static ApplicationContext get() {
        return instance.applicationContext;
    }

}
