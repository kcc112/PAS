package com.pas.pas.service;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.model.events.Event;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.IDeveloperRepository;
import com.pas.pas.repository.interfaces.IEventRepository;
import com.pas.pas.repository.interfaces.ITechnologyRepository;
import com.pas.pas.service.interfaces.IDeveloperService;
import org.springframework.beans.BeanUtils;
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
                switch (developer.getDeveloperTechnology().getTechnologyName()) {
                    case "Ruby On Rails":
                    case "NodeJs":
                        Backend backendDev = new Backend();
                        BeanUtils.copyProperties(developer, backendDev);
                        developerRepository.addDeveloper(backendDev);
                        break;
                    case "React":
                        FrontEnd frontEnd = new FrontEnd();
                        BeanUtils.copyProperties(developer, frontEnd);
                        developerRepository.addDeveloper(frontEnd);
                        break;
                }
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
        Optional<Developer> currentUser = developerRepository.selectDeveloperById(developer.getDeveloperId());

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

    @Override
    public List<Developer> getAllUnemployedDevelopers() {
        return developerRepository.getAllUnemployedDevelopers();
    }
}
