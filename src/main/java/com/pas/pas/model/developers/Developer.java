package com.pas.pas.model.developers;

import com.pas.pas.model.technologies.Technology;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Developer {
    @NotBlank
    private String developerName;
    @NotBlank
    private String developerSurname;
    private Technology developerTechnology;
    private UUID id;

    Developer(String developerName, String developerSurname, Technology developerTechnology, UUID id) {
        this.developerName = developerName;
        this.developerSurname = developerSurname;
        this.developerTechnology = developerTechnology;
        this.id = id;
    }

    public Developer() { }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperSurname() {
        return developerSurname;
    }

    public void setDeveloperSurname(String developerSurname) {
        this.developerSurname = developerSurname;
    }

    public Technology getDeveloperTechnology() {
        return developerTechnology;
    }

    public void setDeveloperTechnology(Technology developerTechnology) {
        this.developerTechnology = developerTechnology;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return developerName + " " + developerSurname + " " + developerTechnology.getTechnologyName();
    }
}
