package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewedProjectsRepository extends JpaRepository<ViewedProjects, Long> {
    boolean existsByUserUserIdAndProjectProjectId(long userId, long projectId);
    ViewedProjects findByUserUserIdAndProjectProjectId(long userId, long projectId);
}
