package com.vaadin.starter.skeleton.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person implements Cloneable {
    private Integer id;
    private String firstName;
    private String lastName;

    private List<Phone> phones = new ArrayList<>();

    /**
     * No-arg constructor required by Crud to be able to instantiate a new bean
     * when the new item button is clicked.
     */
    public Person() {
    }

    public Person(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(Integer id, String firstName, String lastName, String... phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones.addAll(Stream.of(phones).map(phone -> new Phone(phone)).collect(Collectors.toSet()));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public Person clone() {
        try {
            return (Person)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

