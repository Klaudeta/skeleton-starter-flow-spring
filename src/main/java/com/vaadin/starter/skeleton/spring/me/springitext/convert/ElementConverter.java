package com.vaadin.starter.skeleton.spring.me.springitext.convert;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;

public interface ElementConverter<PROPERTY> {

    Element convert(PROPERTY property);
}
