package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.modelHelpers.UserId;
import no.lagalt.lagaltapi.models.modelHelpers.UserUpdate;
import no.lagalt.lagaltapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = BASE_URI_V1 + "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
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
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable long userId ,@RequestBody User newUser) {
        return userService.updateUserById(userId, newUser);
    }

    // delete user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUserById(@PathVariable long userId) {
        return userService.deleteUserById(userId);
    }
}
