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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private static final String EDIT_COLUMN_KEY = "vaadin-crud-edit-column";

    public MainView(@Autowired MessageBean bean) {

        Crud<Person> crud = new Crud<>(Person.class, createPersonEditor());

        PersonDataProvider dataProvider = new PersonDataProvider();

        crud.getGrid().removeColumnByKey("id");
        crud.setDataProvider(dataProvider);
        crud.addSaveListener(e -> dataProvider.persist(e.getItem()));
        crud.addDeleteListener(e -> dataProvider.delete(e.getItem()));

        crud.addThemeVariants(CrudVariant.NO_BORDER);

        Crud.removeEditColumn(crud.getGrid());

//        crud.getGrid().addComponentColumn(person -> new CustomEditColumn<>(person)
//                .visibleWhen(val -> val.getFirstName().startsWith("A"))
//                .executeWhenClicked(() -> Notification.show(person.getFirstName())));

        crud.getGrid().addComponentColumn(person -> {
            CustomEditColumn<Person> button = new CustomEditColumn<>(person);
            button.visibleWhen(val -> val.getFirstName().startsWith("A"));
            button.executeWhenClicked(() -> Notification.show(person.getFirstName()));
            return button;
        })
        .setKey(EDIT_COLUMN_KEY)
        .setWidth("4em")
        .setFlexGrow(0);


        this.add(crud);
    }




    private BinderCrudEditor<Person> createPersonEditor(){
        BeanValidationBinder<Person> binder = new BeanValidationBinder<>(Person.class);

        Div form = new Div();


        TextField name = new TextField("Name");
        TextField surname = new TextField("Surname");

        form.add(name, surname);

        binder.bind(name, "firstName");
        binder.bind(surname, "lastName");


        return new BinderCrudEditor<Person>(binder, form) {
            @Override
            public boolean isValid() {
                return binder.validate().isOk();
            }
        };
    }


}
