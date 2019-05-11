package com.vaadin.starter.skeleton.spring.me.springitext;

import com.itextpdf.text.pdf.PdfPTable;
import java.util.List;

public interface PdfTableGenerator<ITEM, DEF extends PdfDefinition<ITEM>> {

    PdfPTable generate(List<ITEM> items, DEF definition);
}
