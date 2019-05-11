package com.vaadin.starter.skeleton.spring.me.springitext;

import com.itextpdf.text.pdf.PdfPTable;
import java.util.List;

public interface PdfDocument<ITEM, DEF extends PdfDefinition<ITEM>> {

    void setItems(ITEM... items);

    PdfPTable getTable();
}
