package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.services.UsersProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = BASE_URI_V1 + "/users-projects")
public class UserProjectsController {

    @Autowired
    private UsersProjectsService usersProjectsProjectService;

    // get users projects by user ID and project ID
    @GetMapping("/{userId}/{projectId}")
    public ResponseEntity<UsersProjects> getUsersProjectsByUserIdAndProjectId(@PathVariable long userId, @PathVariable long projectId) {
        return usersProjectsProjectService.getUsersProjectsByUserIdAndProjectId(userId, projectId);
    }

    // create user projects
    @PostMapping
    public ResponseEntity<UsersProjects> addUserProject(@RequestBody UsersProjects newUsersProjects) {
        return usersProjectsProjectService.addUserProject(newUsersProjects);
    }

    // update users projects by user ID and project ID
    @PutMapping("/{userId}/{projectId}")
    public ResponseEntity<UsersProjects> updateUsersProjectsByUserIdAndProjectId(@PathVariable long userId, @PathVariable long projectId, @RequestBody UsersProjects newUsersProjects) {
        return usersProjectsProjectService.updateUsersProjectsByUserIdAndProjectId(userId, projectId, newUsersProjects);
    }
}
