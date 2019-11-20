package com.pas.pas.service;

import com.pas.pas.model.technologies.Technology;
import com.pas.pas.repository.interfaces.ITechnologyRepository;
import com.pas.pas.service.interfaces.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService implements ITechnologyService {

    private final ITechnologyRepository technologyRepository;

    @Autowired
    public TechnologyService(ITechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Technology> getAllTechnologies() {
        return technologyRepository.getAllTechnologies();
    }

    @Override
    public Optional<Technology> selectTechnologyByName(String technologyName) {
        return technologyRepository.selectTechnologyByName(technologyName);
    }
}
