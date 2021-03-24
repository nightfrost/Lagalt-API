package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.ProjectCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectCardRepository extends JpaRepository<ProjectCard, Long> {
    Set<ProjectCard> findProjectCardsByProjectProjectId(long projectId);
}
