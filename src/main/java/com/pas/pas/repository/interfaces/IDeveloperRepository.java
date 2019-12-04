package com.pas.pas.repository.interfaces;

import com.pas.pas.model.developers.Developer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDeveloperRepository {
    void addDeveloper(UUID id, Developer developer);
    void destroyDeveloper(UUID id);
    void updateDeveloper(Developer developer);
    Optional<Developer> selectDeveloperById(UUID id);
    List<Developer> getAllDevelopers();
    List<Developer> getAllUnemployedDevelopers();
    List<Developer> getDevelopersByName(String name);

    default void addDeveloper(Developer developer) {
        UUID id = UUID.randomUUID();
        addDeveloper(id, developer);
    }
}
