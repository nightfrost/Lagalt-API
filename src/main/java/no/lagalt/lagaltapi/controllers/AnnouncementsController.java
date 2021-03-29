package no.lagalt.lagaltapi.controllers;

import no.lagalt.lagaltapi.models.Announcement;
import no.lagalt.lagaltapi.services.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = BASE_URI_V1 + "/announcements")
public class AnnouncementsController {

    @Autowired
    private AnnouncementsService announcementsService;

    // get announcement by ID
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable long id) {
        return announcementsService.getAnnouncementById(id);
    }

    // create announcement
    @PostMapping
    public ResponseEntity<Announcement> addAnnouncementToProject(@RequestBody Announcement newAnnouncement) {
        return announcementsService.addAnnouncementToProject(newAnnouncement);
    }

    // update announcement by ID
    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncementById(@PathVariable long id, @RequestBody Announcement newAnnouncement) {
        return announcementsService.updateAnnouncementById(id, newAnnouncement);
    }

    // delete announcement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Announcement> deleteAnnouncementById(@PathVariable long id) {
        return announcementsService.deleteAnnouncementById(id);
    }
}
