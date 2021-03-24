package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.services.ClickedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ClickedProjectService clickedProjectService;

    // create clicked project
    @PostMapping
    public ResponseEntity<ClickedProjects> addClickedProject(@RequestBody ClickedProjects newClickedProject) {
        return clickedProjectService.addClickedProject(newClickedProject);
    }
}
