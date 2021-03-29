package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.ProjectCard;
import no.lagalt.lagaltapi.services.ProjectCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = BASE_URI_V1 + "/project-cards")
public class ProjectCardsController {

    @Autowired
    private ProjectCardsService projectCardsService;

    // get project card by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectCard> getProjectCardById(@PathVariable long id) {
        return projectCardsService.getProjectCardById(id);
    }

    // create project card
    @PostMapping
    public ResponseEntity<ProjectCard> addProjectCardToProject(@RequestBody ProjectCard newProjectCard) {
        return projectCardsService.addProjectCardToProject(newProjectCard);
    }

    // update project card by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProjectCard> updateProjectCardById(@PathVariable long id, @RequestBody ProjectCard newProjectCard) {
        return projectCardsService.updateProjectCardById(id, newProjectCard);
    }

    // delete project card by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectCard> deleteProjectCardById(@PathVariable long id) {
        return projectCardsService.deleteProjectCardById(id);
    }
}
