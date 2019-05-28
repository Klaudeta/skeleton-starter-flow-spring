package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.upload.UploadI18N;
import java.util.Arrays;

public class CustomUploadI18N extends UploadI18N {


    public CustomUploadI18N() {

        setDropFiles(
                new UploadI18N.DropFiles().setOne("Drag here your file please")
                        .setMany("Terhiq dokumentat ketu..."))
                .setAddFiles(new UploadI18N.AddFiles()
                        .setOne("Yes Upload").setMany("Yes Upload many"))
                ;
    }
}
