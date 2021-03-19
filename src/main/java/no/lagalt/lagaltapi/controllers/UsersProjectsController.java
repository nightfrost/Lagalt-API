package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.repositories.UserRepository;
import no.lagalt.lagaltapi.repositories.UsersProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/users-projects")
public class UsersProjectsController {

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UsersProjects> getUserById(@PathVariable long id) {
        User returnUser = new User();
        HttpStatus status;
        if (userRepository.existsById(id)) {
            returnUser = userRepository.findById(id).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnUser, status);
    }

    @PostMapping
    public ResponseEntity<UsersProjects> addUsersProjects(@RequestBody UsersProjects newUsersProjects) {
        UsersProjects usersProjects = usersProjectsRepository.save(newUsersProjects);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(usersProjects, status);
    }
}
