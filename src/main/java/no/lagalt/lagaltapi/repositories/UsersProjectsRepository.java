package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersProjectsRepository extends JpaRepository<UsersProjects, Long> {
    boolean existsByUserUserIdAndProjectProjectId(long userId, long projectId);
    UsersProjects findByUserUserIdAndProjectProjectId(long userId, long projectId);
}
