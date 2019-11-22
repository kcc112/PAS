package com.pas.pas.repository;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.model.technologies.NodeJs;
import com.pas.pas.model.technologies.React;
import com.pas.pas.model.technologies.RubyOnRails;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.IDeveloperRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeveloperRepository implements IDeveloperRepository {

    private List<Developer> developers;

    public DeveloperRepository() {
        this.developers = new ArrayList<>();
        Technology rubyOnRails = new RubyOnRails();
        Technology react = new React();
        Technology nodeJs = new NodeJs();
        developers.add(new Backend("Ernest","Kowalski", rubyOnRails, HelperMethods.randUUID()));
        developers.add(new FrontEnd("Wiktor","Kowalski", react, HelperMethods.randUUID()));
        developers.add(new Backend("Bartek","Kowalski", nodeJs, HelperMethods.randUUID()));
    }

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

    @Override
    public void updateDeveloper(Developer developer) {
        Optional<Developer> userCurrent = selectDeveloperById(developer.getId());
        if (userCurrent.isPresent()) {
            int indexOfPersonToUpdate = developers.indexOf(userCurrent.get());
            developers.set(indexOfPersonToUpdate, developer);
        }
    }
}
