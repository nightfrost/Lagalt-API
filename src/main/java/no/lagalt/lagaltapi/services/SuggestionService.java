package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public ResponseEntity<Set<Long>> getSuggestions(long userId) {
        Set<Project> suggestedProjects = new LinkedHashSet<>();

/*
        // Fetching relevant data points
        Set<String> userSkills = userRepository.findById(userId).get().getUserSkills();

        Set<Long> contributedProjectIds = usersProjectsRepository.findContributedProjectsByUserId(userId);
        Set<Project> contributedProjects = new LinkedHashSet<>();
        contributedProjectIds.forEach(projectId -> contributedProjects.add(projectRepository.findById(projectId).get()));

        Set<Long> clickedProjectIds = clickedProjectsRepository.findClickedProjectsByUserId(userId);
        Set<Project> clickedProjects = new LinkedHashSet<>();
        clickedProjectIds.forEach(projectId -> clickedProjects.add(projectRepository.findById(projectId).get()));

        Set<Long> viewedProjectIds = viewedProjectsRepository.findViewedProjectsByUserId(userId);
        Set<Project> viewedProjects = new LinkedHashSet<>();
        viewedProjectIds.forEach(projectId -> viewedProjects.add(projectRepository.findById(projectId).get()));
*/

        // We can use user-project skills, project type, project tags

        // 1)
        Set<Long> suggestionsFromContributedProjects = userRepository.getSuggestionsFromContributedProjects(userId);
        // 2)
        Set<Long> suggestionsFromClickedProjects = userRepository.getSuggestionsFromClickedProjects(userId);
        // 3)
        Set<Long> userProjectMatchingSkills = userRepository.findUserProjectMatchingSkillsByUserId(userId);

        // 1)
        // Suggestions from user contributed projects : 7, 8
            // types: GAME_DEVELOPMENT
            // skills: 1, 2, 3, 4, 5
            // tags: 1, 2, 3, 4, 5

        // 2)
        // User clicked suggestions: [7], 9, 10

        // 3)
        // User skills suggestions:  [7, 9], 11, 12

        // 4)
        // User viewed suggestions: [7, 8, 9, 10], 13, 14

        return new ResponseEntity<>(suggestionsFromContributedProjects, HttpStatus.OK);
    }
}
