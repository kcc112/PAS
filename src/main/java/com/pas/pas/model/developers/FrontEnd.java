package com.pas.pas.model.developers;

import com.pas.pas.model.technologies.Technology;

import java.util.UUID;

public class FrontEnd extends Developer {
    public FrontEnd(String developerName, String developerSurname, Technology developerTechnology, UUID id) {
        super(developerName, developerSurname, developerTechnology, id);
    }
}
