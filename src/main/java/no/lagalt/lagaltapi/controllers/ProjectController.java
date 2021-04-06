package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.ProjectCard;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
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
    private ProjectRepository projectRepository;

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

    // get all active projects
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

    // reactivate project by project ID
    @PutMapping("/toggle-active-status/{projectId}")
    public ResponseEntity<Project> toggleIsActiveByProjectId(@PathVariable long projectId) {
        return projectService.toggleIsActiveByProjectId(projectId);
    }

    // delete project by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable long id) {
        return projectService.deleteProjectById(id);
    }
}
