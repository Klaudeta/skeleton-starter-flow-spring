package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

public class CustomField extends AbstractCompositeField<Div, CustomField, String> {

    private Span valueSpan = new Span();
    private Div labelSpan = new Div();

    public CustomField() {
        super(null);
        Button change = new Button(VaadinIcon.PENCIL.create(), event -> {
            Dialog dialog = new Dialog();

            TextField value = new TextField();
            Button ok = new Button("ok", e -> {
                setValue(value.getValue());
                dialog.close();
            });
            dialog.add(value, ok);
            dialog.open();
        });

        labelSpan.setWidth("100%");
        getContent().add(labelSpan, valueSpan, change);
    }
    public CustomField(String label) {
        this();
        labelSpan.setText(label);
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
        valueSpan.setText(newPresentationValue);
    }
}
