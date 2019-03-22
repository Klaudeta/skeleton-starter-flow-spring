package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.server.Command;
import java.util.Objects;

@HtmlImport("styles/shared-styles.html")
public class CustomEditColumn<ITEM> extends Div {

    private SerializablePredicate<ITEM> visibleWhen;

    private ITEM item;

    public CustomEditColumn(ITEM item) {
        this.item = item;
        addClassName("crud-edit-button");
    }

    CustomEditColumn visibleWhen(SerializablePredicate<ITEM> visibleWhen){
        Objects.requireNonNull(visibleWhen);
        this.visibleWhen = visibleWhen;
        return this;
    }

    CustomEditColumn executeWhenClicked(Command executeCommand){
        Objects.requireNonNull(executeCommand);
        addClickListener(event -> executeCommand.execute());
        return this;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        this.setVisible(visibleWhen == null || visibleWhen.test(item));
    }

}
