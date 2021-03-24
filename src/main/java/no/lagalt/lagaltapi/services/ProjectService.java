package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

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

    public ResponseEntity<Project> addProject(Project newProject) {
        Project project = projectRepository.save(newProject);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(project, status);
    }

    public ResponseEntity<Project> updateProjectById(long id, Project newProject) {
        HttpStatus status;
        if (projectRepository.existsById(id)) {
            Project project = projectRepository.findById(id).get();

            String newProjectTitle = newProject.getProjectTitle();
            project.setProjectTitle(newProjectTitle);

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
            addProject(newProject);
            status = HttpStatus.CREATED;
            return new ResponseEntity<>(newProject, status);
        }
    }

    public ResponseEntity<Project> deleteProjectById(long id) {
        HttpStatus status;
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }
}
