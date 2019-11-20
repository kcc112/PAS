package com.pas.pas.service;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.IDeveloperRepository;
import com.pas.pas.repository.interfaces.ITechnologyRepository;
import com.pas.pas.service.interfaces.IDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
                developerRepository.addDeveloper(developer);
                break;
            }
        }
    }

    @Override
    public void destroyDeveloper(UUID id) {
        developerRepository.destroyDeveloper(id);
    }
}
