package com.vaadin.starter.skeleton.spring.me.springitext;

import com.vaadin.flow.function.ValueProvider;
import java.util.List;
import java.util.Optional;

public interface PdfDefinition<ITEM> {

    public  enum Orientation{
        HORIZONTAL, VERTICAL
    }

    <PROPERTY> DataColumn<ITEM, PROPERTY> addColumn(String caption, ValueGetter<ITEM, PROPERTY> valueGetter);

    List<DataColumn<ITEM, ?>> getDataColumns();


    void setHeaderConfiguration(ElementConfiguration headerConfiguration);

    void setRowConfiguration(ElementConfiguration rowConfiguration);


    void setOrientation(Orientation orientation);

    Orientation getOrientation();


    default String getFilename(){
        if(!this.getClass().isAnnotationPresent(PdfConfiguration.class) && !this.getClass().isAnnotationPresent(PdfConfiguration.class)){
            throw new IllegalArgumentException("The class " + this.getClass().getCanonicalName()
                    + " should be annotated with the " + PdfConfiguration.class.getCanonicalName() + "annotation" );
        }

        return this.getClass().getAnnotation(PdfConfiguration.class).filename();
    }

    default Optional<FontConfiguration> getFont(){
        if(!this.getClass().isAnnotationPresent(PdfConfiguration.class) && !this.getClass().isAnnotationPresent(PdfConfiguration.class)){
            throw new IllegalArgumentException("The class " + this.getClass().getCanonicalName()
                    + " should be annotated with the " + PdfConfiguration.class.getCanonicalName() + "annotation" );
        }
        FontConfiguration[] fonts = this.getClass().getAnnotation(PdfConfiguration.class).font();

        return Optional.ofNullable(fonts.length > 0 ? fonts[0] : null);
    }

    default Optional<FontConfiguration> getHeaderFont(){
        if(!this.getClass().isAnnotationPresent(PdfConfiguration.class) && !this.getClass().isAnnotationPresent(PdfConfiguration.class)){
            throw new IllegalArgumentException("The class " + this.getClass().getCanonicalName()
                    + " should be annotated with the " + PdfConfiguration.class.getCanonicalName() + "annotation" );
        }
        FontConfiguration[] fonts = this.getClass().getAnnotation(PdfConfiguration.class).headerFont();

        return Optional.ofNullable(fonts.length > 0 ? fonts[0] : null);
    }

    default Optional<FontConfiguration> getRowFont(){
        if(!this.getClass().isAnnotationPresent(PdfConfiguration.class) && !this.getClass().isAnnotationPresent(PdfConfiguration.class)){
            throw new IllegalArgumentException("The class " + this.getClass().getCanonicalName()
                    + " should be annotated with the " + PdfConfiguration.class.getCanonicalName() + "annotation" );
        }
        FontConfiguration[] fonts = this.getClass().getAnnotation(PdfConfiguration.class).rowFont();

        return Optional.ofNullable(fonts.length > 0 ? fonts[0] : null);
    }



    Optional<ElementConfiguration> getRowConfiguration();

    Optional<ElementConfiguration> getHeaderConfiguration();
}
