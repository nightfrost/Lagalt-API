package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.repositories.ClickedProjectsRepository;
import no.lagalt.lagaltapi.repositories.UsersProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/clicked-projects")
public class ClickedProjectsController {

    @Autowired
    private ClickedProjectsRepository clickedProjectsRepository;

    @PostMapping
    public ResponseEntity<ClickedProjects> addClickedProject(@RequestBody ClickedProjects newClickedProject) {
        ClickedProjects clickedProject = clickedProjectsRepository.save(newClickedProject);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(clickedProject, status);
    }
}
