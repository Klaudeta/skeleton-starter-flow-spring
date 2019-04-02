package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me");
        button.setWidth("100%");
        ContextMenu contextMenu = new CustomContextMenu();
        contextMenu.addItem("Item 1", e -> {});
        contextMenu.addItem("Item 2", e -> {});
        contextMenu.setTarget(button);
        contextMenu.setOpenOnClick(true);
        add(button);
    }

}
