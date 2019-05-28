package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {


    private ManagedComponent managedComponent;

    private Dialog dialog = new Dialog();

    public MainView(@Autowired ManagedComponent managedComponent) {
        this.managedComponent = managedComponent;
        this.dialog.add(managedComponent);

        Button button = new Button("Click me", e -> {
            dialog.open();
                });
        add(button);
    }

}
