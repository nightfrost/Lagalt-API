package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
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
@RequestMapping(value = BASE_URI_V1 + "/users-projects")
public class UserProjectsController {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @PostMapping
    public ResponseEntity<UsersProjects> addUserProject(@RequestBody UsersProjects newUsersProjects) {
        UsersProjects usersProjects = usersProjectsRepository.save(newUsersProjects);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(usersProjects, status);
    }
}
