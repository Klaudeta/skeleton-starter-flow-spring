package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {

        ChargebeeCheckout checkout = new ChargebeeCheckout("Do checkout");
        checkout.setCustomerEmail("weare@weare.com");

        /*This listener for now with not be fired as the checkout process uses
        the redirect_url mechanism after a successful checkout.*/
        checkout.addSuccessListener(event -> {
            Notification.show(event.getHostedPageId());
        });

        checkout.addCancelListener(event -> {
            Notification.show("Checkout closed before completed!");
        });


        TextField email = new TextField();
        email.addValueChangeListener(e -> {
            checkout.setCustomerEmail(e.getValue());
        });

        add( email, checkout);
    }

}
