package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the my-template template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("my-template")
@JsModule("./src/my-template.js")
public class MyTemplate extends PolymerTemplate<MyTemplate.MyTemplateModel> {

    /**
     * Creates a new MyTemplate.
     */
    public MyTemplate() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between MyTemplate and my-template
     */
    public interface MyTemplateModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
