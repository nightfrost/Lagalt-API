package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.models.ProjectCard;
import no.lagalt.lagaltapi.repositories.ProjectCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/project-cards")
public class ProjectCardController {

    @Autowired
    private ProjectCardRepository projectCardRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectCard> getProjectCardById(@PathVariable long id) {
        ProjectCard returnProjectCard = new ProjectCard();
        HttpStatus status;
        if (projectCardRepository.existsById(id)) {
            returnProjectCard = projectCardRepository.findById(id).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnProjectCard, status);
    }

    @PostMapping
    public ResponseEntity<ProjectCard> addProjectCardToProject(@RequestBody ProjectCard newProjectCard) {
        ProjectCard projectCard = projectCardRepository.save(newProjectCard);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(projectCard, status);
    }
}
