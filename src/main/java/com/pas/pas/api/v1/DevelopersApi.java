package com.pas.pas.api.v1;

import com.pas.pas.model.developers.Developer;
import com.pas.pas.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/developers")
@RestController
public class DevelopersApi {

    private final DeveloperService developerService;

    @Autowired
    public DevelopersApi(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public Developer addDeveloper(@RequestBody Developer developer, @RequestParam(name = "technology") String technology) {
        developerService.addDeveloper(developer, technology);
        return developer;
    }

    @GetMapping
    public List<Developer> getAllDevelopers()  {
        return developerService.getAllDevelopers();
    }

    @GetMapping(path = "{id}")
    public Developer getDevelopersByName(@PathVariable UUID id) {
        return developerService.selectDeveloperById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDeveloper(@PathVariable UUID id) {
        developerService.destroyDeveloper(id);
    }
}
