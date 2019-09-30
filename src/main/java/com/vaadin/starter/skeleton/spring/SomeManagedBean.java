package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class SomeManagedBean extends VerticalLayout implements SomeInterface {


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if(attachEvent.isInitialAttach()){
            add(new MyTemplate());
        }
    }

    @Override
    public Component asComponent() {
        return this;
    }
}
