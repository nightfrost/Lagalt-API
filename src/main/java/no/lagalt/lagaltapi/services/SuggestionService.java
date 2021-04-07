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

    public ResponseEntity<Set<Project>> getSuggestions(long userId) {
        Set<Project> suggestedProjects = new LinkedHashSet<>();

        // Extracting relevant data
        Set<Long> suggestionsFromContributedProjects = userRepository.getSuggestionsFromContributedProjects(userId);
        System.out.println(suggestionsFromContributedProjects);
        Set<Long> suggestionsFromClickedProjects = userRepository.getSuggestionsFromClickedProjects(userId);
        System.out.println(suggestionsFromClickedProjects);
        Set<Long> userProjectMatchingSkills = userRepository.findUserProjectMatchingSkillsByUserId(userId);
        System.out.println(userProjectMatchingSkills);
        Set<Long> suggestionsFromViewedProjects = userRepository.getSuggestionsFromViewedProjects(userId);
        System.out.println(suggestionsFromViewedProjects);

        // Collecting suggestions to return
        if (suggestionsFromContributedProjects.size() > 0 && !suggestionsFromContributedProjects.contains(null)) {
            suggestionsFromContributedProjects.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        }
        if (suggestionsFromClickedProjects.size() > 0 && !suggestionsFromClickedProjects.contains(null)) {
            suggestionsFromClickedProjects.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        }
        if (userProjectMatchingSkills.size() > 0 && !userProjectMatchingSkills.contains(null)) {
            userProjectMatchingSkills.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        }
        if (suggestionsFromViewedProjects.size() > 0 && !suggestionsFromViewedProjects.contains(null)) {
            suggestionsFromViewedProjects.forEach(projectId -> suggestedProjects.add(projectRepository.findById(projectId).get()));
        }

        // If there are no matching projects, some other active projects are suggested
        if (suggestedProjects.size() == 0) {
            if (projectRepository.existsActiveProjects()) {
                Set<Project> userUnrelatedActiveProjects = projectRepository.getUserUnrelatedActiveProjects(userId);
                return new ResponseEntity<>(userUnrelatedActiveProjects, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(suggestedProjects, HttpStatus.OK);
    }
}
