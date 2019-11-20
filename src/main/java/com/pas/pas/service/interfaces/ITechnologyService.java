package com.pas.pas.service.interfaces;

import com.pas.pas.model.technologies.Technology;

import java.util.List;
import java.util.Optional;

public interface ITechnologyService {
    List<Technology> getAllTechnologies();
    Optional<Technology> selectTechnologyByName(String technologyName);
}
