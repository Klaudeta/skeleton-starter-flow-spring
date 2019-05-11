package com.vaadin.starter.skeleton.spring.me.springitext.convert;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.starter.skeleton.spring.me.springitext.PdfContext;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("singleton")
public class EnumConverter<PROPERTY extends Enum> implements PropertyConverter<PROPERTY> {

    @Override
    public String convert(PROPERTY property) {

        if(property == null) return null;

        String toLower = property.name().replaceAll("_+", " ").toLowerCase();
        return toLower.replaceFirst(Character.toString(toLower.charAt(0)), Character.toString(Character.toUpperCase(toLower.charAt(0))));

    }

    public static <P extends Enum> String doConvert(P property){
         EnumConverter<P> eConverter = PdfContext.get().getBean(EnumConverter.class);
        return eConverter.convert(property);
    }

    public static <PROPERTY extends Enum>EnumConverter<PROPERTY> get(){
        return  PdfContext.get().getBean(EnumConverter.class);
    }
}
