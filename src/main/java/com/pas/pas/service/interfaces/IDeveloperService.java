package com.pas.pas.service.interfaces;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.technologies.Technology;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDeveloperService {
    List<Developer> getAllDevelopers();
    Optional<Developer> selectDeveloperById(UUID id);
    void addDeveloper(Developer developer, Technology technology);
    void updateDeveloper(Developer developer);
    void destroyDeveloper(UUID id);
}
