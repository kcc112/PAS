package com.pas.pas.model.developers;

import com.pas.pas.model.technologies.Technology;

import java.util.UUID;

public class Backend extends Developer {
    public Backend(String developerName, String developerSurname, Technology developerTechnology, UUID developerId) {
        super(developerName, developerSurname, developerTechnology, developerId);
    }

    public Backend() {}
}
