package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.modelHelpers.UserRelatedProject;
import no.lagalt.lagaltapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
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
    *   Get data on projects related to a user by user ID - custom query function to fetch data on user related projects
    *   from multiple tables: user role, project title, project type, application status, project progress, project
    *   status
     */
    @GetMapping("/{userId}/related-projects")
    public ResponseEntity<Set<UserRelatedProject>> getUserRelatedProjectsByUserId(@PathVariable long userId) {
        return userService.getUserRelatedProjectsByUserId(userId);
    }

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
