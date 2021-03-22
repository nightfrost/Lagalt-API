package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;
import no.lagalt.lagaltapi.repositories.ViewedProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/viewed-projects")
public class ViewedProjectsController {

    @Autowired
    private ViewedProjectsRepository viewedProjectsRepository;

    @PostMapping
    public ResponseEntity<ViewedProjects> addViewedProject(@RequestBody ViewedProjects newViewedProject) {
        ViewedProjects viewedProject = viewedProjectsRepository.save(newViewedProject);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(viewedProject, status);
    }
}
