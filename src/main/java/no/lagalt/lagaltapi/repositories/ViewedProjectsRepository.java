package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewedProjectsRepository extends JpaRepository<ViewedProjects, Long> {
    boolean existsByUserUserIdAndProjectProjectId(long userId, long projectId);
    ViewedProjects findByUserUserIdAndProjectProjectId(long userId, long projectId);

/*
    // Select projects that the user has viewed
    @Query(value = "SELECT project_project_id FROM viewed_projects WHERE user_user_id = ?1", nativeQuery = true)
    Set<Long> findViewedProjectsByUserId(long userId);
*/
}
