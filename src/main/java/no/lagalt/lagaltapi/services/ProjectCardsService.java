package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.ProjectCard;
import no.lagalt.lagaltapi.repositories.ProjectCardRepository;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectCardsService {

    @Autowired
    private ProjectCardRepository projectCardRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<ProjectCard> getProjectCardById(long projectCardId) {
        ProjectCard returnProjectCard = null;
        HttpStatus status;
        if (projectCardRepository.existsById(projectCardId)) {
            returnProjectCard = projectCardRepository.findById(projectCardId).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnProjectCard, status);
    }

    public ResponseEntity<Set<ProjectCard>> getProjectCardsByProjectId(long projectId) {
        Set<ProjectCard> returnProjectCards = new HashSet<>();
        HttpStatus status;
        if (projectRepository.existsById(projectId)) {
            returnProjectCards = projectCardRepository.findProjectCardsByProjectProjectId(projectId);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnProjectCards, status);
    }

    public ResponseEntity<ProjectCard> addProjectCardToProject(ProjectCard newProjectCard) {
        ProjectCard projectCard = projectCardRepository.save(newProjectCard);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(projectCard, status);
    }

    public ResponseEntity<ProjectCard> updateProjectCardById(long id, ProjectCard newProjectCard) {
        HttpStatus status;
        if (projectCardRepository.existsById(id)) {
            ProjectCard projectCard = projectCardRepository.findById(id).get();

            String newProjectCardTitle = newProjectCard.getProjectCardTitle();
            projectCard.setProjectCardTitle(newProjectCardTitle);

            String newProjectCardText = newProjectCard.getProjectCardText();
            projectCard.setProjectCardText(newProjectCardText);

            Timestamp announcementUpdatedAt = newProjectCard.getProjectCardUpdatedAt();
            projectCard.setProjectCardUpdatedAt(announcementUpdatedAt);

            projectCardRepository.save(projectCard);
            status = HttpStatus.OK;
            return new ResponseEntity<>(projectCard, status);
        } else {
            addProjectCardToProject(newProjectCard);
            status = HttpStatus.CREATED;
            return new ResponseEntity<>(newProjectCard, status);
        }
    }

    public ResponseEntity<ProjectCard> deleteProjectCardById(long id) {
        HttpStatus status;
        if (projectCardRepository.existsById(id)) {
            projectCardRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }
}
