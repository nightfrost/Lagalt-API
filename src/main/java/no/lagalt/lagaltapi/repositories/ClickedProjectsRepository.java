package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickedProjectsRepository extends JpaRepository<ClickedProjects, Long> {
    boolean existsByUserUserIdAndProjectProjectId(long userId, long projectId);
    ClickedProjects findByUserUserIdAndProjectProjectId(long userId, long projectId);

/*
    // Select projects that the user has clicked
    @Query(value = "SELECT project_project_id FROM clicked_projects WHERE user_user_id = ?1", nativeQuery = true)
    Set<Long> findClickedProjectsByUserId(long userId);
*/
}
