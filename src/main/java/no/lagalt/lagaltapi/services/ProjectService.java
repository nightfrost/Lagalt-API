package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.enums.ApprovalStatus;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;
import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;
import no.lagalt.lagaltapi.models.modelHelpers.ProjectUpdate;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
import no.lagalt.lagaltapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersProjectsService usersProjectsService;

    @Autowired
    private ClickedProjectService clickedProjectService;

    @Autowired
    private ViewedProjectService viewedProjectService;

    public ResponseEntity<Project> getProjectById(long projectId) {
        Project returnProject = null;
        HttpStatus status;
        if (projectRepository.existsById(projectId)) {
            returnProject = projectRepository.findById(projectId).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnProject, status);
    }

    public ResponseEntity<Set<Project>> getAllActiveProjects(String industry, String status, String search, short limit) {
        Set<Project> returnProjects = null;
        HttpStatus httpStatus;
        if (projectRepository.existsActiveProjects()) {
            returnProjects = projectRepository.getAllActiveProjects(
                    industry == null ? "" : industry,
                    status == null ? "" : status,
                    search == null ? "" : search.toLowerCase(),
                    limit
            );
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnProjects, httpStatus);
    }

    public ResponseEntity<Project> addProject(Project newProject) {
        HttpStatus status;
        if (!projectRepository.existsByProjectTitle(newProject.getProjectTitle())) {
            User user = userRepository.findById(newProject.getUserId()).get();
            Project project = projectRepository.save(newProject);

            UsersProjects newUsersProjects = new UsersProjects(user, newProject, "Project Owner");
            newUsersProjects.setAdmin(true);
            newUsersProjects.setHasContributed(true);
            newUsersProjects.setApprovalStatus(ApprovalStatus.APPROVED);
            usersProjectsService.addUserProject(newUsersProjects);

            ClickedProjects newClickedProject = new ClickedProjects(user, newProject, new Timestamp(System.currentTimeMillis()));
            clickedProjectService.addClickedProject(newClickedProject);

            ViewedProjects newViewedProject = new ViewedProjects(user, newProject, new Timestamp(System.currentTimeMillis()));
            viewedProjectService.addViewedProject(newViewedProject);
            status = HttpStatus.CREATED;

            project = projectRepository.findById(project.getProjectId()).get();
            return new ResponseEntity<>(project, status);
        } else {
            status = HttpStatus.CONFLICT;
            return new ResponseEntity<>(null, status);
        }
    }

    public ResponseEntity<Project> updateProjectById(long id, Project newProject) {
        HttpStatus status;
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).get();

            if (!newProject.getProjectTitle().equals(project.getProjectTitle())) {
                if (!projectRepository.existsByProjectTitle(newProject.getProjectTitle())) {
                    String newProjectTitle = newProject.getProjectTitle();
                    project.setProjectTitle(newProjectTitle);
                } else {
                    status = HttpStatus.CONFLICT;
                    return new ResponseEntity<>(null, status);
                }
            }

            ProjectProgress newProjectProgress = newProject.getProjectProgress();
            project.setProjectProgress(newProjectProgress);

            String newProjectDescription = newProject.getProjectDescription();
            project.setProjectDescription(newProjectDescription);

            ProjectType newProjectType = newProject.getProjectType();
            project.setProjectType(newProjectType);

            Set<String> newProjectPhotos = newProject.getProjectPhotos();
            project.setProjectPhotos(newProjectPhotos);

            String newProjectBackgroundPhoto = newProject.getProjectBackgroundPhoto();
            project.setProjectBackgroundPhoto(newProjectBackgroundPhoto);

            String newExternalUrl = newProject.getExternalUrl();
            project.setExternalUrl(newExternalUrl);

            Set<String> newProjectSkills = newProject.getProjectSkills();
            project.setProjectSkills(newProjectSkills);

            Set<String> newProjectTags = newProject.getProjectTags();
            project.setProjectTags(newProjectTags);

            projectRepository.save(project);
            status = HttpStatus.OK;
            return new ResponseEntity<>(project, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    public ResponseEntity<Project> toggleIsActiveByProjectId(long id) {
        HttpStatus status;
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).get();

            boolean isActive = project.isActive();
            project.setActive(!isActive);

            projectRepository.save(project);
            status = HttpStatus.OK;
            return new ResponseEntity<>(project, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }

    public ResponseEntity<Project> deleteProjectById(long id) {
        HttpStatus status;
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).get();
            project.setActive(false);
            projectRepository.save(project);
            status = HttpStatus.OK;
            return new ResponseEntity<>(project, status);
        } else {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(null, status);
        }
    }
}
