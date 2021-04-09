package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.modelHelpers.UserRelatedProject;
import no.lagalt.lagaltapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> getUserById(long id) {
        User returnUser = null;
        HttpStatus status;
        if (userRepository.existsById(id)) {
            returnUser = userRepository.findById(id).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnUser, status);
    }

    /*
    *   `UserRelatedProject` is an interface that Hibernate uses to map the custom query results to a custom POJO
    *   (cf. UserRelatedProject in src/main/java/no/lagalt/lagaltapi/models/modelHelpers, getUserRelatedProjectsByUserId
    *   in UserController and getUserRelatedProjectsByUserId in UserRepository)
    */
    public ResponseEntity<Set<UserRelatedProject>> getUserRelatedProjectsByUserId(long userId) {
        HttpStatus status;
        if (userRepository.existsById(userId)) {
            Set<UserRelatedProject> userRelatedProjects = userRepository.getUserRelatedProjectsByUserId(userId);
            status = HttpStatus.OK;
            return new ResponseEntity<>(userRelatedProjects, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    public ResponseEntity<User> addUser(User newUser) {
        HttpStatus status;
        if (!userRepository.existsByUserEmail(newUser.getUserEmail())) {
            User user = userRepository.save(newUser);
            status = HttpStatus.CREATED;
            return new ResponseEntity<>(user, status);
        } else {
            status = HttpStatus.CONFLICT;
            return new ResponseEntity<>(null, status);
        }
    }

    public ResponseEntity<User> updateUserById(long id, User newUser) {
        HttpStatus status;
        // Check if the user exists
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();

            String newUserName = newUser.getUserName();
            user.setUserName(newUserName);

            // Check if the updated email would conflict with existing emails
            if (!newUser.getUserEmail().equals(user.getUserEmail())) {
                // If no conflict - update
                if(!userRepository.existsByUserEmail(newUser.getUserEmail())) {
                    String newUserEmail = newUser.getUserEmail();
                    user.setUserEmail(newUserEmail);
                // Else - report conflict
                } else {
                    status = HttpStatus.CONFLICT;
                    return new ResponseEntity<>(null, status);
                }
            }

            Set<String> newUserSkills = newUser.getUserSkills();
            user.setUserSkills(newUserSkills);

            String newUserPortfolio = newUser.getUserPortfolio();
            user.setUserPortfolio(newUserPortfolio);

            String newUserDescription = newUser.getUserDescription();
            user.setUserDescription(newUserDescription);

            boolean newUserVisibility = newUser.isUserVisibility();
            user.setUserVisibility(newUserVisibility);

            userRepository.save(user);
            status = HttpStatus.OK;
            return new ResponseEntity<>(user, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    public ResponseEntity<User> deleteUserById(long id) {
        HttpStatus status;
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }
}
