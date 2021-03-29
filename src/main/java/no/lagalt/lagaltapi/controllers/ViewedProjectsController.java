package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;
import no.lagalt.lagaltapi.services.ViewedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = BASE_URI_V1 + "/viewed-projects")
public class ViewedProjectsController {

    @Autowired
    private ViewedProjectService viewedProjectService;

    // create viewed project
    @PostMapping
    public ResponseEntity<ViewedProjects> addViewedProject(@RequestBody ViewedProjects newViewedProject) {
        return viewedProjectService.addViewedProject(newViewedProject);
    }
}
