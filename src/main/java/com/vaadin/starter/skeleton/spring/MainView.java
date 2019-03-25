package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.dom.Element;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private static final String EDIT_COLUMN_KEY = "vaadin-crud-edit-column";
    private final Crud<Person> crud;

    public MainView(@Autowired MessageBean bean) {

        crud = new Crud<>(Person.class, createPersonEditor());

        PersonDataProvider dataProvider = new PersonDataProvider();

        crud.getGrid().removeColumnByKey("id");
        crud.setDataProvider(dataProvider);
        crud.addSaveListener(e -> dataProvider.persist(e.getItem()));
        crud.addDeleteListener(e -> dataProvider.delete(e.getItem()));

        crud.addThemeVariants(CrudVariant.NO_BORDER);



//        crud.getGrid().addComponentColumn(person -> new CustomEditColumn<>(person)
//                .visibleWhen(val -> val.getFirstName().startsWith("A"))
//                .executeWhenClicked(() -> Notification.show(person.getFirstName())));

        Crud.removeEditColumn(crud.getGrid());
        crud.getGrid().addComponentColumn(person -> {
            CustomEditColumn<Person> button = new CustomEditColumn<>(person);
            button.visibleWhen(val -> !val.getFirstName().startsWith("A"));
            button.executeWhenClicked(() -> {
                crud.getEditor().setItem(person);
                crud.setOpened(true);

                // The two above lines with be replaced with this commented line below
                // in the new release of Crud Flow component
                // More details in https://github.com/vaadin/vaadin-crud-flow/pull/123
//                crud.edit(person, Crud.EditMode.EXISTING_ITEM);
            });

            return button;
        })
        .setKey(EDIT_COLUMN_KEY)
        .setWidth("4em")
        .setFlexGrow(0);

        this.add(crud);

    }

    private BinderCrudEditor<Person> createPersonEditor() {
        BeanValidationBinder<Person> binder = new BeanValidationBinder<>(Person.class);

        Div form = new Div();


        TextField name = new TextField("Name");
        CustomField surname = new CustomField("Surname");

        CrudField<Phone> phoneCrudField = new CrudField<>(Phone.class, createPhoneEditor());

        form.add(name, surname, phoneCrudField);

        binder.bind(name, "firstName");
        binder.bind(surname, "lastName");
        binder.bind(phoneCrudField, "phones");

        binder.addValueChangeListener(e -> {
           Component component =  (Component)e.getHasValue();
           // This JS execution trigger the change event on client side resulting
            // in the Save button becoming dirty and so enabled.
            // This will allow all all custom fields used in the Crud Editor
            // to enable the Save button once their value is changed.

            component.getElement().executeJavaScript("setTimeout(function(){$0.dispatchEvent(new CustomEvent('change', {bubbles: true, composed: true}));},0)", component.getElement());
        });

        return new BinderCrudEditor<Person>(binder, form) {
            @Override
            public boolean isValid() {
                return binder.validate().isOk();
            }
        };
    }


    private BinderCrudEditor<Phone> createPhoneEditor() {
        BeanValidationBinder<Phone> binder = new BeanValidationBinder<>(Phone.class);

        Div form = new Div();

        TextField number = new TextField("Number");

        form.add(number);

        binder.bind(number, "number");


        return new BinderCrudEditor<Phone>(binder, form) {
            @Override
            public boolean isValid() {
                return binder.validate().isOk();
            }
        };
    }


}
