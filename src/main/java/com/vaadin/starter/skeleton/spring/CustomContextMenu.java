package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.HtmlImport;

@HtmlImport("styles/shared-styles.html")
public class CustomContextMenu extends ContextMenu {
    {
        getElement().setAttribute("theme", "custom-context-menu-overlay");

        addOpenedChangeListener(event -> {
            if(event.isOpened()){
                getElement().executeJavaScript(
                        "setTimeout(" +
                                "function(){" +
                                "const overlay = $0.$.overlay; " +
                                "overlay.style.setProperty('--menu-top', overlay.style.top, 'important');" +
                                "overlay.style.setProperty('--menu-right', overlay.style.right, 'important');" +
                                "overlay.style.setProperty('--menu-left', overlay.style.left, 'important');" +
                                "},0)", getElement());
            }
        });
    }
}
