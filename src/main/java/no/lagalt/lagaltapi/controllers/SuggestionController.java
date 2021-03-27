package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.repositories.*;
import no.lagalt.lagaltapi.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/suggestions")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    @GetMapping("/{userId}")
    public ResponseEntity<Set<Long>> getSuggestions(@PathVariable long userId) {
        return suggestionService.getSuggestions(userId);
    }
}
