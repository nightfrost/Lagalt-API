package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // get project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable long id) {
        Project returnProject = new Project();
        HttpStatus status;
        if (projectRepository.existsById(id)) {
            returnProject = projectRepository.findById(id).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnProject, status);
    }


    // add new project
    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project newProject) {
        Project project = projectRepository.save(newProject);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(project, status);
    }
}
