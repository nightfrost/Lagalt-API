package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.repositories.AnnouncementRepository;
import no.lagalt.lagaltapi.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@RequestMapping(value = BASE_URI_V1 + "/announcements")
public class AnnouncementsController {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable long id) {
        Announcement returnAnnoucement = new Announcement();
        HttpStatus status;
        if (announcementRepository.existsById(id)) {
            returnAnnoucement = announcementRepository.findById(id).get();
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnAnnoucement, status);
    }

    @PostMapping
    public ResponseEntity<Announcement> addAnnouncementToProject(@RequestBody Announcement newAnnouncement) {
        Announcement announcement = announcementRepository.save(newAnnouncement);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(announcement, status);
    }
}
