package com.vaadin.starter.skeleton.spring;

import com.chargebee.Environment;
import com.chargebee.ListResult;
import com.chargebee.models.Customer;
import com.chargebee.models.Subscription;
import com.vaadin.flow.component.button.Button;
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
        checkout.setDataCbPlanId("zero");
        checkout.setCustomerEmail("weare@weare.com");

        checkout.addLoadedListener(event -> {
            Notification.show("Checkout process started...");
        });

        /*This listener for now with not be fired as the checkout process uses
        the redirect_url mechanism after a successful checkout.*/
        checkout.addSuccessListener(event -> {
            Notification.show(event.getHostedPageId());
        });

        checkout.addCloseListener(event -> {
            if(event.isProcessCancelled())
                Notification.show("Checkout was cancelled before completing!");
            else
                Notification.show("Checkout closed after completing successfully!");
        });


        TextField email = new TextField();
        email.addValueChangeListener(e -> {
            checkout.setCustomerEmail(e.getValue());
        });

        Button delete =new Button("Delete Subscription", e -> this.deleteCustomerSubscriptions(email.getValue()));

        add( email, checkout, delete);
    }


    private void deleteCustomerSubscriptions(String email){
        Environment.configure("we-r-test","test_tEZcuYi2xvHsmQ4yhp8qmWT8lQXE99EHB");
        try {
            ListResult customer_result = Customer.list().email().is(email).request();

            if (customer_result.isEmpty()) {
                return ;
            }

            Customer customer = customer_result.get(0).customer();
            ListResult subscriptions = Subscription.list().customerId().is(customer.id()).request();

            if (subscriptions.isEmpty()) {
                return;
            }
            for (ListResult.Entry entry : subscriptions) {
                Subscription subscription = entry.subscription();
                Subscription.delete(subscription.id()).request();
            }

        }catch(Exception e){

        }
    }

}
