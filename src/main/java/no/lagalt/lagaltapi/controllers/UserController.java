package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

/*
    // get all user projects by user ID
    @GetMapping("/{userId}/projects")
    public ResponseEntity<Set<Project>> getUserProjectsByUserId(@PathVariable long userId) {
        Set<Project> userProjects = new HashSet<>();
        HttpStatus status;
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).get();
            userProjects = user.getUserProjects().stream().map(projects -> projects.getProject()).collect(Collectors.toSet());
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(userProjects, status);
    }
*/

    // create new user
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    // update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable long id, @RequestBody User newUser) {
        return userService.updateUserById(id, newUser);
    }

    // delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable long id) {
        return userService.deleteUserById(id);
    }
}
