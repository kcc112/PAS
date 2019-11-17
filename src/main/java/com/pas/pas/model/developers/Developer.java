package com.pas.pas.model.developers;

import com.pas.pas.model.technologies.Technology;

public class Developer {
    private String name;
    private String surname;
    private Technology technology;

    Developer(String name, String surname, Technology technology) {
        this.name = name;
        this.surname = surname;
        this.technology = technology;
    }

    public Developer() { }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
