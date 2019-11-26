package com.pas.pas.model.developers;

import com.pas.pas.model.technologies.Technology;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Developer {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 12)
    @Pattern(regexp = "[A-z][a-z]", message = "Invalid name")
    private String developerName;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 12)
    @Pattern(regexp = "[A-z][a-z]", message = "Invalid surname")
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
