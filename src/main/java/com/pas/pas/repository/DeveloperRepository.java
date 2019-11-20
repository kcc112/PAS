package com.pas.pas.repository;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.repository.interfaces.IDeveloperRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeveloperRepository implements IDeveloperRepository {

    private List<Developer> developers = new ArrayList<>();

    @Override
    public void addDeveloper(UUID id, Developer developer) {
        developer.setId(id);
        developers.add(developer);
    }

    @Override
    public void destroyDeveloper(UUID id) {
        Optional<Developer> developerToDelete = selectDeveloperById(id);
        developerToDelete.ifPresent(developer -> developers.remove(developer));
    }

    @Override
    public Optional<Developer> selectDeveloperById(UUID id) {
        return developers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developers;
    }
}
