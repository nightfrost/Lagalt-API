package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.repositories.ClickedProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClickedProjectService {

    @Autowired
    private ClickedProjectsRepository clickedProjectsRepository;

    public ResponseEntity<ClickedProjects> addClickedProject(@RequestBody ClickedProjects newClickedProject) {
        HttpStatus status;
        ClickedProjects clickedProject;
        long userId = newClickedProject.getUser().getUserId();
        long projectId = newClickedProject.getProject().getProjectId();
        if (clickedProjectsRepository.existsByUserUserIdAndProjectProjectId(userId, projectId)) {
            clickedProject = clickedProjectsRepository.findByUserUserIdAndProjectProjectId(userId, projectId);
            status = HttpStatus.SEE_OTHER;
        } else {
            clickedProject = clickedProjectsRepository.save(newClickedProject);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(clickedProject, status);
    }
}
