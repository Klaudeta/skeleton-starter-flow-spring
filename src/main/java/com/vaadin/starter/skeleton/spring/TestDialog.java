package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.treegrid.TreeGrid;
import java.util.concurrent.Executors;

public class TestDialog extends Dialog {

    public TestDialog() {
        loadGrid();
    }

    private void loadGrid() {
        getElement().getNode().runWhenAttached(ui -> {

            Executors.newSingleThreadExecutor().execute(() -> {

                TreeGrid<MyUser> grid = new TreeGrid<>();
                grid.setWidth("400px");
                grid.setHeight("300px");
                grid.addColumn(MyUser::getEmail).setHeader("Email");

//                grid.setItems(users);

                ui.access(() -> {
                    add(grid);
                });
            });
        });
    }

    public static class MyUser{
        private String email;

        public String getEmail() {
            return email;
        }
    }
}
