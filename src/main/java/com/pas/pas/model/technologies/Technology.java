package com.pas.pas.model.technologies;

public class Technology {
    private String name;

    Technology(String name) {
        this.name = name;
    }

    public Technology() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
