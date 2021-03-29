package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.services.ClickedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@CrossOrigin(origins = "*")
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
