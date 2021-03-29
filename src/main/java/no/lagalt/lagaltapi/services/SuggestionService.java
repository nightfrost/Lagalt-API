package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SuggestionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersProjectsRepository usersProjectsRepository;

    @Autowired
    private ClickedProjectsRepository clickedProjectsRepository;

    @Autowired
    private ViewedProjectsRepository viewedProjectsRepository;

    public ResponseEntity<Set<Project>> getSuggestions(long userId) {
        Set<Project> suggestedProjects = new LinkedHashSet<>();

        // Extracting relevant data
        Set<Long> suggestionsFromContributedProjects = userRepository.getSuggestionsFromContributedProjects(userId);
        Set<Long> suggestionsFromClickedProjects = userRepository.getSuggestionsFromClickedProjects(userId);
        Set<Long> userProjectMatchingSkills = userRepository.findUserProjectMatchingSkillsByUserId(userId);
        Set<Long> suggestionsFromViewedProjects = userRepository.getSuggestionsFromViewedProjects(userId);

        // Collecting suggestions to return
        suggestionsFromContributedProjects.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        suggestionsFromClickedProjects.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        userProjectMatchingSkills.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        suggestionsFromViewedProjects.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));

        return new ResponseEntity<>(suggestedProjects, HttpStatus.OK);
    }
}
