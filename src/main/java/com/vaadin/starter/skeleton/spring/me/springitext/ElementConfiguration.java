package com.vaadin.starter.skeleton.spring.me.springitext;

import com.itextpdf.text.pdf.PdfPCell;

@FunctionalInterface
public interface ElementConfiguration {

    void apply(PdfPCell cell);
}
