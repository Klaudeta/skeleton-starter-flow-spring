package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.ui.LoadMode;

@Tag("quill-editor")
@JavaScript(value = "https://cdn.quilljs.com/1.3.6/quill.js", loadMode = LoadMode.EAGER)
@StyleSheet(value = "https://cdn.quilljs.com/1.3.6/quill.snow.css", loadMode = LoadMode.EAGER)
public class QuillEditor extends Component implements HasComponents, HasSize {
    Div editor = new Div();

    Div exported = new Div();

    public QuillEditor() {

        editor.setId("editor-quill");
        Button export = new Button("Export");
        export.addClickListener(buttonClickEvent -> {
            String outerHTML = editor.getElement().getProperty("innerHTML");
            exported.getElement().setProperty("innerHTML", outerHTML);
            System.out.println(editor.getElement().getChild(0).getProperty("innerHTML"));
        });


        editor.setWidthFull();
        editor.setHeightFull();
        this.setWidth("400px");
        this.setHeight("500px");
        add(export, editor, exported);
        getElement().getNode().runWhenAttached(ui -> {
            getElement().executeJavaScript("setTimeout(function(){" +
                    "var toolbarOptions = [\n" +
                    "  ['bold', 'italic', 'underline', 'strike'],\n" +
                    "  ['blockquote', 'code-block'],\n" +
                    "\n" +
                    "  [{ 'header': 1 }, { 'header': 2 }],\n" +
                    "  [{ 'list': 'ordered'}, { 'list': 'bullet' }],\n" +
                    "  [{ 'script': 'sub'}, { 'script': 'super' }],\n" +
                    "  [{ 'indent': '-1'}, { 'indent': '+1' }],\n" +
                    "  [{ 'direction': 'rtl' }],\n" +
                    "\n" +
                    "  [{ 'size': ['small', false, 'large', 'huge'] }],\n" +
                    "  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],\n" +
                    "\n" +
                    "  [{ 'color': [] }, { 'background': [] }],\n" +
                    "  [{ 'font': [] }],\n" +
                    "  [{ 'align': [] }],\n" +
                    "\n" +
                    "  ['clean'] \n" +
                    "];\n" +
                    "var options = {\n" +
                    "  debug: 'info',\n" +
                    "  modules: {\n" +
                    "    toolbar: toolbarOptions\n" +
                    "  },\n" +
                    "  placeholder: 'Compose an epic...',\n" +
                    "  theme: 'snow'\n" +
                    "};" +
                    "var editor = new Quill($0, options);},0)", editor.getElement());
        });
    }
}
