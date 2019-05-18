package com.vaadin.starter.skeleton.spring;

import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private String ORIGINAL = "original.txt";
    private String REVISED = "revised.txt";

    private Div originalDiv = new Div();
    private Div revisedDiv = new Div();

    public MainView(@Autowired MessageBean bean) {

        Button button = new Button("Click me",
                e -> {
                    compare();
                });
        originalDiv.setWidth("90vh");
        originalDiv.setHeightFull();
        revisedDiv.setWidth("90vh");
        revisedDiv.setHeightFull();

        Div scrollablePrimary = new Div(originalDiv);
        Div scrollableSecondary = new Div(revisedDiv);

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        splitLayout.addToPrimary(scrollablePrimary);
        splitLayout.addToSecondary(scrollableSecondary);
        add(button, splitLayout);

    }

    private void setOriginalContent(String content){
        originalDiv.getElement().setProperty("innerHTML", content);
    }

    private void setRevisedContent(String content){
        revisedDiv.getElement().setProperty("innerHTML", content);
    }


    private List<String> getLines(String filename){
        try {
            return Files.readAllLines(Paths.get(this.getClass().getResource("/" + filename).getPath()));

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void patch(){
        Patch<String> patch = null;
        try {
            patch = DiffUtils.diff(getLines(ORIGINAL), getLines(REVISED));

            for (AbstractDelta<String> delta : patch.getDeltas()) {
                System.out.println(delta);
            }

        } catch (DiffException ex) {
            ex.printStackTrace();
        }
    }

    public void compare(){
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> f ? "<del>" : "</del>")
                .newTag(f -> f ? "<b>" : "</b>")
                .build();
        List<DiffRow> rows = null;
        try {
            rows = generator.generateDiffRows(getLines(ORIGINAL), getLines(REVISED));
        } catch (DiffException e) {
            e.printStackTrace();
        }

        System.out.println("|original|new|");
        System.out.println("|--------|---|");

        setOriginalContent(rows.stream().map(DiffRow::getOldLine).collect(Collectors.joining("<br>")));
        setRevisedContent(rows.stream().map(DiffRow::getNewLine).collect(Collectors.joining("<br>")));

        for (DiffRow row : rows) {
            System.out.println("|" + row.getOldLine() + "|" + row.getNewLine() + "|");
        }
    }
}
