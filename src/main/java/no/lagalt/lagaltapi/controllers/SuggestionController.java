package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    /*
    *   An endpoint that implements the new content algorithm that provides suggestions for logged-in users based on
    *   projects that they have previously contributed to, clicked on or viewed. It also accounts for user's skills and
    *   also suggests other active projects if there are no related ones to suggest.
    */
    @GetMapping("/{userId}")
    public ResponseEntity<Set<Project>> getSuggestions(@PathVariable long userId) {
        return suggestionService.getSuggestions(userId);
    }
}
