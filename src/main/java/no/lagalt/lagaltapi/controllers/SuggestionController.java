package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.modelHelpers.UserId;
import no.lagalt.lagaltapi.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @GetMapping
    public ResponseEntity<Set<Project>> getSuggestions(@RequestBody UserId userId) {
        return suggestionService.getSuggestions(userId.getUserId());
    }
}
