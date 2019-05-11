package com.vaadin.starter.skeleton.spring.me.springitext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractPdfDefinition<ITEM> implements PdfDefinition<ITEM> {

    private final List<DataColumn<ITEM, ?>> dataColumns = new ArrayList<>();
    private ElementConfiguration headerConfiguration;
    private ElementConfiguration rowConfiguration;
    private Orientation orientation = Orientation.HORIZONTAL;

    public void setOrientation(Orientation orientation){

        this.orientation = orientation;
    }

    @Override
    public Orientation getOrientation() {
        return null;
    }

    @Override
    public <PROPERTY> DataColumn<ITEM, PROPERTY> addColumn(String header, ValueGetter<ITEM, PROPERTY> valueGetter) {
        DataColumn<ITEM, PROPERTY> dataColumn = new DataColumn<>( header, valueGetter);
        dataColumns.add(dataColumn);
        return dataColumn;
    }

    @Override
    public List<DataColumn<ITEM, ?>> getDataColumns() {
        return null;
    }

    @Override
    public void setHeaderConfiguration(ElementConfiguration headerConfiguration) {

        this.headerConfiguration = headerConfiguration;
    }

    @Override
    public void setRowConfiguration(ElementConfiguration rowConfiguration) {

        this.rowConfiguration = rowConfiguration;
    }

    @Override
    public Optional<ElementConfiguration> getRowConfiguration() {
        return Optional.ofNullable(rowConfiguration);
    }

    @Override
    public Optional<ElementConfiguration> getHeaderConfiguration() {
        return Optional.ofNullable(headerConfiguration);
    }

}
