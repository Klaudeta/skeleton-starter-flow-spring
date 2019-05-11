package com.vaadin.starter.skeleton.spring.me.springitext;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PdfDocumentBean<ITEM, DEF extends PdfDefinition<ITEM>> implements PdfDocument<ITEM, DEF> {

    private DEF definition;
    private List<ITEM> items;
    private PdfPTable table;

    public PdfDocumentBean(DEF definition) {
        this.definition = definition;
    }

    @Override
    public void setItems(ITEM... items){
        if(definition.getOrientation() == PdfDefinition.Orientation.VERTICAL && items.length > 1) {
            throw new RuntimeException("Only one item can be rendered vertically on the table!");
        }
        this.items = Arrays.asList(items);

    }

    public void build(){

        if(definition.getOrientation() == PdfDefinition.Orientation.HORIZONTAL){
            table = new PdfPTable(definition.getDataColumns().size());
            definition.getDataColumns().forEach(this::buildHeader);
            items.forEach(item -> buildRow(table, item));
        }else{
            table = new PdfPTable(2);

            if(!items.isEmpty()){
                definition.getDataColumns().forEach(col -> this.buildRow(items.get(0), col));
            }
        }

    }

    private void buildRow(ITEM item, DataColumn<ITEM,?> itemDataColumn) {
        final PdfPCell headerCell = new PdfPCell();

        Phrase phrase = new Phrase(itemDataColumn.getHeader());

        definition.getFont().ifPresent(f -> phrase.setFont(createFont(f)));
        definition.getHeaderFont().ifPresent(hf -> phrase.setFont(createFont(hf)));
        headerCell.setPhrase(phrase);
        table.addCell(headerCell);


        PdfPCell cell = new PdfPCell();
        itemDataColumn.getColumnConfiguration().ifPresent(conf -> conf.apply(cell));

        final Phrase cellPhase = new Phrase(itemDataColumn.getConvertedValue(item));

        definition.getFont().ifPresent(f -> cellPhase.setFont(createFont(f)));
        definition.getRowFont().ifPresent(rf -> cellPhase.setFont(createFont(rf)));
        cell.setPhrase(cellPhase);
        table.addCell(cell);

    }


    private void buildRow(PdfPTable table, ITEM item) {
        definition.getDataColumns().forEach(col -> {
            PdfPCell cell = new PdfPCell();
            col.getColumnConfiguration().ifPresent(conf -> conf.apply(cell));

            Phrase phrase;

            if(col.getElementConverter().isPresent()){
                 phrase = new Phrase();
                 phrase.add(col.getConvertedElement(item));
            }else{
                phrase = new Phrase(col.getConvertedValue(item));
            }

            definition.getFont().ifPresent(f -> phrase.setFont(createFont(f)));
            definition.getRowFont().ifPresent(rf -> phrase.setFont(createFont(rf)));

            cell.setPhrase(phrase);
            table.addCell(cell);
        });
    }

    private Font createFont(FontConfiguration font){
        return FontFactory.getFont(font.fontName(), font.encoding(), font.size(), font.style(), font.color().getBaseColor());
    }

    private PdfPCell buildHeader(DataColumn<ITEM,?> itemDataColumn) {
        PdfPCell cell = new PdfPCell();
        itemDataColumn.getColumnConfiguration().ifPresent(conf -> conf.apply(cell));

        Phrase phrase = new Phrase(itemDataColumn.getHeader());
        definition.getFont().ifPresent(f -> phrase.setFont(createFont(f)));
        definition.getHeaderFont().ifPresent(hf -> phrase.setFont(createFont(hf)));
        cell.setPhrase(phrase);
        return cell;
    }


    @Override
    public PdfPTable getTable(){
        if(table == null){
            build();
        }
        return table;
    }
}
