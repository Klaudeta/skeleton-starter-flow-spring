package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import java.util.concurrent.Executors;

public class TestDialog extends Notification {

    public TestDialog() {

    }

    private void loadGrid() {

                getUI().get().access(() -> {
                    Grid<MyUser> grid = new Grid<>();
                    grid.setWidth("400px");
                    grid.setHeight("300px");
                    grid.addColumn(MyUser::getEmail).setHeader("Email");
                    add(grid);
                });
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        loadGrid();
    }

    public static class MyUser{
        private String email;

        public String getEmail() {
            return email;
        }
    }
}
