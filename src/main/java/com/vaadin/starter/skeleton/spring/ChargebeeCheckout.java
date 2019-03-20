package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A Designer generated component for the chargebee-checkout.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("chargebee-checkout")
@HtmlImport("src/chargebee-checkout.html")
public class ChargebeeCheckout extends PolymerTemplate<ChargebeeCheckout.ChargebeeCheckoutModel> {

    private final Set<ComponentEventListener<SuccessEvent>> successListeners = new LinkedHashSet<>();
    private final Set<ComponentEventListener<CancelEvent>> cancelListeners = new LinkedHashSet<>();


    /**
     * Creates a new ChargebeeCheckout.
     */
    public ChargebeeCheckout() {
        // You can initialise any data required for the connected UI components here.
        setListeners();
    }

    public ChargebeeCheckout(String label) {
        this();
        setLabel(label);
    }

    private void setListeners(){
        ComponentUtil.addListener(this, SuccessEvent.class, (ComponentEventListener)
                ((ComponentEventListener<SuccessEvent>) e -> {
                    successListeners.forEach(listener -> listener.onComponentEvent(e));
                }));

        ComponentUtil.addListener(this, CancelEvent.class, (ComponentEventListener)
                ((ComponentEventListener<CancelEvent>) e -> {
                    cancelListeners.forEach(listener -> listener.onComponentEvent(e));
                }));
    }
    /**
     * Sets the label to be displayed on the component.
     *
     * @param label
     */
    public void setLabel(String label){
        getModel().setLabel(label);
    }

    /**
     * Sets the customer's email to be used for the checkout process.
     *
     * @param email parameter defining the customer's email
     */
    public void setCustomerEmail(String email){
        getModel().setCustomerEmail(email);
    }

    /**
     * Sets the type of the ChargeBee service to be used.
     *
     * @param dataCbType parameter defining the type of the service.
     *                   default value is <code>checkout</code>
     */
    public void setDataCbType(String dataCbType){
        getModel().setDataCbType(dataCbType);
    }

    /**
     * Defines the plan id to be used in the checkout process.
     *
     * @param dataCbPlanId parameter defining the plan id.
     *                     Default value is <code>cbdemo_free</code>
     */
    public void setDataCbPlanId(String dataCbPlanId){
        getModel().setDataCbPlanId(dataCbPlanId);
    }
    /**
     * Registers a listener to be notified when the checkout process was successful.
     *
     * @param listener a listener to be notified
     * @return a handle that can be used to unregister the listener
     */
    public Registration addSuccessListener(ComponentEventListener<SuccessEvent> listener) {
        successListeners.add(listener);
        return () -> successListeners.remove(listener);
    }


    /**
     * Registers a listener to be notified when the checkout process is cancelled.
     *
     * @param listener a listener to be notified
     *
     * @return a handle that can be used to unregister the listener
     */
    public Registration addCancelListener(ComponentEventListener<CancelEvent> listener) {
        cancelListeners.add(listener);
        return () -> cancelListeners.remove(listener);
    }


    /**
     * This model binds properties between ChargebeeCheckout and chargebee-checkout.html
     */
    public interface ChargebeeCheckoutModel extends TemplateModel {

        void setDataCbType(String dataCbType);

        void setDataCbPlanId(String dataCbPlanId);

        void setLabel(String label);

        void setCustomerEmail(String email);

    }


    /**
     * Event fired when the checkout completed successfully.
     *
     */
    @DomEvent("success")
    public static class SuccessEvent extends ComponentEvent<ChargebeeCheckout> {

        private String hostedPageId;

        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         * @param hostedPageId the hostedPageId of the checkout process
         */
        public SuccessEvent(ChargebeeCheckout source, boolean fromClient,
                        @EventData("detail.hostedPageId") String hostedPageId) {
            super(source, fromClient);

            this.hostedPageId = hostedPageId;
        }

        public String getHostedPageId() {
            return hostedPageId;
        }
    }

    /**
     * Event fired when the checkout was cancelled.
     *
     */
    @DomEvent("cancel")
    public static class CancelEvent extends ComponentEvent<ChargebeeCheckout> {


        /**
         * Creates a new event using the given source and indicator whether the
         * event originated from the client side or the server side.
         *
         * @param source     the source component
         * @param fromClient <code>true</code> if the event originated from the client
         */
        public CancelEvent(ChargebeeCheckout source, boolean fromClient) {
            super(source, fromClient);

        }
    }
}
