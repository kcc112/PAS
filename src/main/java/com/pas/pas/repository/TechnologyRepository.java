package com.pas.pas.repository;

import com.pas.pas.model.technologies.NodeJs;
import com.pas.pas.model.technologies.React;
import com.pas.pas.model.technologies.RubyOnRails;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.ITechnologyRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TechnologyRepository implements ITechnologyRepository {

    private List<Technology> technologies;

    public TechnologyRepository() {
        this.technologies = new ArrayList<>();
        technologies.add(new RubyOnRails());
        technologies.add(new React());
        technologies.add(new NodeJs());
    }

    @Override
    public Optional<Technology> selectTechnologyByName(String technologyName) {
        return technologies.stream()
                .filter(technology -> technology.getTechnologyName().equals(technologyName))
                .findFirst();
    }

    @Override
    public List<Technology> getAllTechnologies() {
        return technologies;
    }

    @Override
    public List<Technology>  getAllTechnologiesFrontEnd() {
        List<Technology> frontendTechnologies = new ArrayList<>();
        for (Technology tech : technologies) {
            if (tech.getTechnologyName().equals("React")) {
                frontendTechnologies.add(tech);
            }
        }
        return frontendTechnologies;
    }

    @Override
    public List<Technology> getAllTechnologiesBackEnd() {
        List<Technology> backendTechnologies = new ArrayList<>();
        for (Technology tech : technologies) {
            if (tech.getTechnologyName().equals("Ruby On Rails") || tech.getTechnologyName().equals("NodeJs") ) {
                backendTechnologies.add(tech);
            }
        }
        return backendTechnologies;
    }
}
