package com.pas.pas.api.v1;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/front-end")
    public void addDeveloperFrontEnd(@RequestBody FrontEnd developer) {
        developerService.addDeveloper(developer, developer.getDeveloperTechnology());
    }

    @PostMapping("/back-end")
    public void addDeveloperBackEnd(@RequestBody Backend developer) {
        developerService.addDeveloper(developer, developer.getDeveloperTechnology());
    }

    @GetMapping
    public List<Developer> getDevelopers(@RequestParam(name = "id", required = false) UUID id)  {
        if (id == null || id.toString().isBlank()) {
            return developerService.getAllDevelopers();
        } else {
            List<Developer> developers = new ArrayList<>();
            developers.add(developerService.selectDeveloperById(id).orElse(null));
            return developers;
        }
    }

    @PutMapping("/front-end")
    public void updateDeveloperFrontEnd(@RequestBody FrontEnd developer) {
        developerService.updateDeveloper(developer);
    }

    @PutMapping("/back-end")
    public void updateDeveloperBackEnd(@RequestBody Backend developer) {
        developerService.updateDeveloper(developer);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDeveloper(@PathVariable UUID id) {
        developerService.destroyDeveloper(id);
    }
}
