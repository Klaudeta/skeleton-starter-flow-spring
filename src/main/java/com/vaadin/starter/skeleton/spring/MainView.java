package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.component.crud.CrudVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.AbstractDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.dom.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        List<Person> personList = new ArrayList<>(new ArrayList<>(Arrays.asList(new Person(1, "MyName", "MySurname"))));
        CrudDataProvider dataProvider = new CrudDataProvider(personList);

        Crud<Person> crud = new Crud<>(Person.class, createPersonEditor());

        crud.getGrid().removeColumnByKey("id");
        crud.setDataProvider(dataProvider );
        crud.addSaveListener(e -> dataProvider.saveItem(e.getItem()));
        crud.addDeleteListener(e -> dataProvider.removeItem(e.getItem()));

        this.add(crud);
    }


    private BinderCrudEditor<Person> createPersonEditor() {
        BeanValidationBinder<Person> binder = new BeanValidationBinder<>(Person.class);

        Div form = new Div();

        TextField name = new TextField("Name");
        CustomField surname = new CustomField("Surname");

        form.add(name, surname);

        binder.bind(name, "firstName");
        binder.bind(surname, "lastName");

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

    private class CrudDataProvider extends AbstractDataProvider<Person, CrudFilter> {
        final List<Person> DATABASE;

        public CrudDataProvider(List<Person> items) {
            DATABASE = items;
        }

        @Override
        public boolean isInMemory() {
            return true;
        }

        @Override
        public int size(Query<Person, CrudFilter> query) {
            return DATABASE.size();
        }

        @Override
        public Stream<Person> fetch(Query<Person, CrudFilter> query) {
            return DATABASE.stream();
        }

        public void saveItem(Person item){
            if(!DATABASE.contains(item)){
                DATABASE.add(item);
            }
        }

        public void removeItem(Person item){
            DATABASE.remove(item);
        }
    }

    public static class Person {
        private Integer id;
        private String firstName;
        private String lastName;

        public Person() {
        }

        public Person(Integer id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }


}



//          binder.addValueChangeListener(e -> {
//             Component component =  (Component)e.getHasValue();
//             // This JS execution trigger the change event on client side resulting
//              // in the Save button becoming dirty and so enabled.
//              // This will allow all all custom fields used in the Crud Editor
//              // to enable the Save button once their value is changed.
//
//              component.getElement().executeJavaScript("setTimeout(function(){$0.dispatchEvent(new CustomEvent('change', {bubbles: true, composed: true}));},0)", component.getElement());
//          });
