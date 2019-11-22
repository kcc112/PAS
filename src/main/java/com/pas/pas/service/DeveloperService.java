package com.pas.pas.service;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.IDeveloperRepository;
import com.pas.pas.repository.interfaces.ITechnologyRepository;
import com.pas.pas.service.interfaces.IDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeveloperService implements IDeveloperService {

    private final IDeveloperRepository developerRepository;
    private final ITechnologyRepository technologyRepository;

    @Autowired
    public DeveloperService(IDeveloperRepository developerRepository, ITechnologyRepository technologyRepository) {
        this.developerRepository = developerRepository;
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.getAllDevelopers();
    }

    @Override
    public void addDeveloper(Developer developer, Technology technology) {

        List<Technology> technologies = technologyRepository.getAllTechnologies();

        for (Technology tech : technologies) {

            if(tech.getTechnologyName().equals(technology.getTechnologyName())) {
                developer.setDeveloperTechnology(technology);
                String developerName = developer.getDeveloperName();
                String developerSurname = developer.getDeveloperSurname();
                Technology developerTechnology = developer.getDeveloperTechnology();
                UUID id = developer.getId();

                switch (developer.getDeveloperTechnology().getTechnologyName()) {
                    case "Ruby On Rails":
                    case "NodeJs":
                        Backend devBack = new Backend(developerName, developerSurname, developerTechnology, id);
                        developerRepository.addDeveloper(devBack);
                        break;
                    case "React":
                        FrontEnd devFrontReact = new FrontEnd(developerName, developerSurname, developerTechnology, id);
                        developerRepository.addDeveloper(devFrontReact);
                        break;
                }
            }
        }
    }

    @Override
    public void destroyDeveloper(UUID id) {
        developerRepository.destroyDeveloper(id);
    }

    @Override
    public void updateDeveloper(Developer developer) {
        Optional<Developer> currentUser = developerRepository.selectDeveloperById(developer.getId());

        if (currentUser.isPresent()) {

            Technology newTechnology = developer.getDeveloperTechnology();
            currentUser.get().setDeveloperTechnology(newTechnology);

            if(!developer.getDeveloperName().isBlank()) {
                String name = developer.getDeveloperName();
                currentUser.get().setDeveloperName(name);
            }

            if(!developer.getDeveloperSurname().isBlank()) {
                String surname = developer.getDeveloperSurname();
                currentUser.get().setDeveloperSurname(surname);
            }
            developerRepository.updateDeveloper(currentUser.get());
        }
    }

    @Override
    public Optional<Developer> selectDeveloperById(UUID id) {
        return developerRepository.selectDeveloperById(id);
    }
}
