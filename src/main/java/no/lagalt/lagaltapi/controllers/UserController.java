package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
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


    // add new user
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User user = userRepository.save(newUser);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(user, status);
    }
}
