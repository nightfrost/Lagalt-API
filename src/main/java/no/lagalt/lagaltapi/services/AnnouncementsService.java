package no.lagalt.lagaltapi.services;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.repositories.AnnouncementRepository;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
public class AnnouncementsService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<Announcement> getAnnouncementById(long announcementId) {
        Announcement returnAnnouncement = null;
        HttpStatus status;
        if (announcementRepository.existsById(announcementId)) {
            returnAnnouncement = announcementRepository.findById(announcementId).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnAnnouncement, status);
    }

    public ResponseEntity<Set<Announcement>> getAnnouncementsByProjectId(long projectId) {
        Set<Announcement> returnAnnoucements = new HashSet<>();
        HttpStatus status;
        if (projectRepository.existsById(projectId)) {
            returnAnnoucements = announcementRepository.findAnnouncementsByProjectProjectId(projectId);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnAnnoucements, status);
    }

    public ResponseEntity<Announcement> addAnnouncementToProject(Announcement newAnnouncement) {
        Announcement announcement = announcementRepository.save(newAnnouncement);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(announcement, status);
    }

    public ResponseEntity<Announcement> updateAnnouncementById(long id, Announcement newAnnouncement) {
        HttpStatus status;
        if (announcementRepository.existsById(id)) {
            Announcement announcement = announcementRepository.findById(id).get();

            String newAnnouncementTitle = newAnnouncement.getAnnouncementTitle();
            announcement.setAnnouncementTitle(newAnnouncementTitle);

            String newAnnouncementText = newAnnouncement.getAnnouncementText();
            announcement.setAnnouncementText(newAnnouncementText);

            Timestamp announcementUpdatedAt = newAnnouncement.getAnnouncementUpdatedAt();
            announcement.setAnnouncementUpdatedAt(announcementUpdatedAt);

            announcementRepository.save(announcement);
            status = HttpStatus.OK;
            return new ResponseEntity<>(announcement, status);
        } else {
            addAnnouncementToProject(newAnnouncement);
            status = HttpStatus.CREATED;
            return new ResponseEntity<>(newAnnouncement, status);
        }
    }

    public ResponseEntity<Announcement> deleteAnnouncementById(long id) {
        HttpStatus status;
        if (announcementRepository.existsById(id)) {
            announcementRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(null, status);
    }
}
