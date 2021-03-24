package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Set<Announcement> findAnnouncementsByProjectProjectId(long projectId);
}
