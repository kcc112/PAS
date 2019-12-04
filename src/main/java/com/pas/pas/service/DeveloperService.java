package com.pas.pas.service;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.events.Event;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.IDeveloperRepository;
import com.pas.pas.repository.interfaces.IEventRepository;
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
    private final IEventRepository eventRepository;

    @Autowired
    public DeveloperService(IDeveloperRepository developerRepository, ITechnologyRepository technologyRepository, IEventRepository eventRepository) {
        this.developerRepository = developerRepository;
        this.technologyRepository = technologyRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.getAllDevelopers();
    }

    @Override
    public void addDeveloper(Developer developer, Technology technology) {
        List<Technology> technologies = technologyRepository.getAllTechnologies();

        for (Technology tech : technologies) {
            if (tech.getTechnologyName().equals(technology.getTechnologyName())) {
                developer.setDeveloperTechnology(technology);
                developerRepository.addDeveloper(developer);
            }
        }
    }

    @Override
    public void destroyDeveloper(UUID id) {
        Optional<Event> event = eventRepository.getEventsWithDevelopId(id);
        if (event.isPresent()) {
            event.get().setDeveloper(null);
            developerRepository.destroyDeveloper(id);
        } else {
            developerRepository.destroyDeveloper(id);
        }
    }

    @Override
    public void updateDeveloper(Developer developer) {
        developerRepository.updateDeveloper(developer);
    }

    @Override
    public Optional<Developer> selectDeveloperById(UUID id) {
        return developerRepository.selectDeveloperById(id);
    }

    @Override
    public List<Developer> getAllUnemployedDevelopers() {
        return developerRepository.getAllUnemployedDevelopers();
    }
}
