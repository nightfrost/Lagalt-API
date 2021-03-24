package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;
import no.lagalt.lagaltapi.repositories.ViewedProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ViewedProjectService {

    @Autowired
    private ViewedProjectsRepository viewedProjectsRepository;

    public ResponseEntity<ViewedProjects> addViewedProject(@RequestBody ViewedProjects newViewedProject) {
        HttpStatus status;
        ViewedProjects viewedProjects;
        long userId = newViewedProject.getUser().getUserId();
        long projectId = newViewedProject.getProject().getProjectId();
        if (viewedProjectsRepository.existsByUserUserIdAndProjectProjectId(userId, projectId)) {
            viewedProjects = viewedProjectsRepository.findByUserUserIdAndProjectProjectId(userId, projectId);
            status = HttpStatus.SEE_OTHER;
        } else {
            viewedProjects = viewedProjectsRepository.save(newViewedProject);
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(viewedProjects, status);
    }
}
