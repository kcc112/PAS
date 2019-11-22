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
    private UUID developerId;

    Developer(String developerName, String developerSurname, Technology developerTechnology, UUID developerId) {
        this.developerName = developerName;
        this.developerSurname = developerSurname;
        this.developerTechnology = developerTechnology;
        this.developerId = developerId;
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

    public UUID getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(UUID developerId) {
        this.developerId = developerId;
    }

    @Override
    public String toString() {
        return developerName + " " + developerSurname + " " + developerTechnology.getTechnologyName();
    }
}
