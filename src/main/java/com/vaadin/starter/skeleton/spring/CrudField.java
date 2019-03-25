package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.component.crud.CrudVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.provider.AbstractDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Custom Crud Field to be used to treat List of items as a value.
 * The component can be used with @{@link com.vaadin.flow.data.binder.Binder}
 * to bind List of items belonging to same type as a single value.
 * @param <E>
 */
public class CrudField<E> extends AbstractCompositeField<Crud<E>, CrudField<E>, List<E>> {



    private class CrudDataProvider extends AbstractDataProvider<E, CrudFilter>{

        @Override
        public boolean isInMemory() {
            return true;
        }

        @Override
        public int size(Query<E, CrudFilter> query) {
            return getValue().size();
        }

        @Override
        public Stream<E> fetch(Query<E, CrudFilter> query) {
            return getValue().stream();
        }
    }


    public CrudField(Class<E> clazz, CrudEditor<E> editor) {
        super(new ArrayList<>());

        Crud<E> crud = getContent();

        crud.setBeanType(clazz);
        crud.setEditor(editor);

        crud.setDataProvider(new CrudDataProvider());
        crud.addSaveListener(e -> this.saveItem(e.getItem()));
        crud.addDeleteListener(e -> this.removeItem(e.getItem()));
        crud.addThemeVariants(CrudVariant.NO_BORDER);
        crud.getGrid().setPageSize(3);
        crud.setToolbar(new Div());

    }


    private void saveItem(E item){

        List<E> oldValue = new ArrayList<>(getValue());
        if(!oldValue.contains(item)){
            getValue().add(item);
        }

        ComponentUtil.fireEvent(this,
                createValueChange(oldValue, false));

    }

    private void removeItem(E item){

        List<E> oldValue = new ArrayList<>(getValue());
        getValue().remove(item);

        ComponentUtil.fireEvent(this,
                createValueChange(oldValue, false));
    }

    private AbstractField.ComponentValueChangeEvent<CrudField<E>, List<E>> createValueChange(List<E> oldValue,
                                                                            boolean fromClient) {
        return new AbstractField.ComponentValueChangeEvent<>(this, this, oldValue,
                fromClient);

    }
    @Override
    protected void setPresentationValue(List<E> newPresentationValue) {
        getContent().getDataProvider().refreshAll();
    }
}
