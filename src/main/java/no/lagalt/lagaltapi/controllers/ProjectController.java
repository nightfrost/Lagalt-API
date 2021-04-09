package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.ProjectCard;
import no.lagalt.lagaltapi.services.AnnouncementsService;
import no.lagalt.lagaltapi.services.ProjectCardsService;
import no.lagalt.lagaltapi.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AnnouncementsService announcementsService;

    @Autowired
    private ProjectCardsService projectCardsService;

    // get project by project ID
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable long projectId) {
        return projectService.getProjectById(projectId);
    }

    /*
    *   Get all active projects - an endpoint that has the implementation of search functionality for matches across
    *   project title, type, description, required skills and tags. It also allows filtering by industry and project
    *   status as well as limiting the amount of search results. In addition, it serves as an endpoint to fetch projects
    *   for anonymous users
    */
    @GetMapping("/projects")
    public ResponseEntity<Set<Project>> getAllActiveProjects(
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "10") short limit) {
        return projectService.getAllActiveProjects(industry, status, search, limit);
    }

    // get all announcements by project ID
    @GetMapping("/{projectId}/announcements")
    public ResponseEntity<Set<Announcement>> getAllAnnouncementsByProjectId(@PathVariable long projectId) {
        return announcementsService.getAnnouncementsByProjectId(projectId);
    }

    // get all project cards by project ID
    @GetMapping("/{projectId}/project-cards")
    public ResponseEntity<Set<ProjectCard>> getAllProjectCardsByProjectId(@PathVariable long projectId) {
        return projectCardsService.getProjectCardsByProjectId(projectId);
    }

    // create project
    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project newProject) {
        return projectService.addProject(newProject);
    }

    // update project by project ID
    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProjectById(@PathVariable long projectId, @RequestBody Project newProject) {
        return projectService.updateProjectById(projectId, newProject);
    }

    /*
    *   Reactivate project by project ID - an endpoint that implements a toggle function to change the status of a
    *   project whether it's active or inactive. The purpose of the endpoint is in view of never actually deleting a
    *   project in order to be able to retain activity history for the suggestion algorithm
    */
    @PutMapping("/toggle-active-status/{projectId}")
    public ResponseEntity<Project> toggleIsActiveByProjectId(@PathVariable long projectId) {
        return projectService.toggleIsActiveByProjectId(projectId);
    }

    // delete project by ID - the project is not actually deleted, but it's isActive status is set to `false`
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable long id) {
        return projectService.deleteProjectById(id);
    }
}
