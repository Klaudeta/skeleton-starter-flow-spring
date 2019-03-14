package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the charge-bee-view.html template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("charge-bee-view")
@HtmlImport("src/charge-bee-view.html")
public class ChargeBeeView extends PolymerTemplate<ChargeBeeView.ChargeBeeViewModel> {

    /**
     * Creates a new ChargeBeeView.
     */
    public ChargeBeeView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between ChargeBeeView and charge-bee-view.html
     */
    public interface ChargeBeeViewModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
