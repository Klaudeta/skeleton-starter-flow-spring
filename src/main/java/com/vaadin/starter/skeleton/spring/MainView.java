package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.ui.Transport;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@Push(transport = Transport.LONG_POLLING)
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        Button button = new Button("Click me",
                e -> {
                    new TestDialog().open();
                });
        add(button);
    }

}
