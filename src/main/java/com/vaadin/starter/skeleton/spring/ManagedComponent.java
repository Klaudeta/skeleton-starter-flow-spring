package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class ManagedComponent extends VerticalLayout {

    private Upload upload;

    public ManagedComponent() {
        upload = new Upload();
        upload.setI18n(new CustomUploadI18N());
        this.add(upload);
    }

}
