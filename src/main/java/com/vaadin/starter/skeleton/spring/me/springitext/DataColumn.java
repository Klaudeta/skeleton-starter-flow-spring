package com.vaadin.starter.skeleton.spring.me.springitext;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.starter.skeleton.spring.me.springitext.convert.ElementConverter;
import com.vaadin.starter.skeleton.spring.me.springitext.convert.PropertyConverter;
import java.util.Objects;
import java.util.Optional;

public class  DataColumn<ROW, PROPERTY> {


    private String header;
    private ValueGetter<ROW, PROPERTY> valueGetter;
    private PropertyConverter<PROPERTY> propertyConverter;
    private Class<? extends PropertyConverter> propertyConverterClazz;
    private ElementConfiguration columnConfiguration;
    private ElementConverter<PROPERTY> elementConverter;

    public DataColumn(String header, ValueGetter<ROW, PROPERTY> valueGetter) {

        this.header = header;
        this.valueGetter = valueGetter;
    }

    public String getHeader() {
        return header;
    }

    public ValueGetter<ROW, PROPERTY> getValueGetter() {
        return valueGetter;
    }


    public DataColumn<ROW, PROPERTY> withColumnConfiguration(ElementConfiguration columnConfiguration){
        this.columnConfiguration = columnConfiguration;
        return this;
    }

    public DataColumn<ROW, PROPERTY> withConverter(PropertyConverter<PROPERTY> propertyConverter){
        this.propertyConverter = propertyConverter;
        return this;
    }

    public <CONVERTER_CLASS extends PropertyConverter<PROPERTY>> DataColumn<ROW, PROPERTY> withConverter(Class<CONVERTER_CLASS> propertyConverterClazz){
        this.propertyConverterClazz = propertyConverterClazz;
        return this;
    }


    public String getConvertedValue(ROW bean){
        PROPERTY value = getValueGetter().apply(bean);
        return getPropertyConverter().map(converter -> converter.convert(value)).orElse(Optional.ofNullable(value).map(Object::toString).orElse(""));
    }

    public Optional<PropertyConverter<PROPERTY>> getPropertyConverter() {
        if(propertyConverter != null){
            return Optional.ofNullable(propertyConverter);
        }
        else {
            return Optional.ofNullable(propertyConverterClazz).map(clazz -> Optional.ofNullable((PropertyConverter<PROPERTY>) PdfContext.get().getBean(clazz))).orElse(Optional.empty());
        }
    }

    public Optional<ElementConfiguration> getColumnConfiguration() {
        return Optional.ofNullable(columnConfiguration);
    }

    public DataColumn<ROW, PROPERTY> withConverter(ElementConverter<PROPERTY> elementConverter){
        this.elementConverter = elementConverter;
        return this;
    }

    public Optional<ElementConverter<PROPERTY>> getElementConverter() {
        return Optional.ofNullable(elementConverter);
    }

    public Element getConvertedElement(ROW bean){
        PROPERTY value = getValueGetter().apply(bean);
        return getElementConverter().map(converter -> converter.convert(value)).orElse(Optional.ofNullable(value).map(val -> new Phrase(val.toString())).orElse(new Phrase()));
    }


}
