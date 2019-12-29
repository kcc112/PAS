package com.pas.pas.api.v1;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.Developer;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity addDeveloperFrontEnd(@Validated @RequestBody FrontEnd developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.addDeveloper(developer, developer.getDeveloperTechnology());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/back-end")
    public ResponseEntity addDeveloperBackEnd(@Validated @RequestBody Backend developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.addDeveloper(developer, developer.getDeveloperTechnology());
        return new ResponseEntity(HttpStatus.CREATED);
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
    public ResponseEntity updateDeveloperFrontEnd(@Validated @RequestBody FrontEnd developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.updateDeveloper(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/back-end")
    public ResponseEntity updateDeveloperBackEnd(@Validated @RequestBody Backend developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.updateDeveloper(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDeveloper(@PathVariable UUID id) {
        developerService.destroyDeveloper(id);
    }
}
