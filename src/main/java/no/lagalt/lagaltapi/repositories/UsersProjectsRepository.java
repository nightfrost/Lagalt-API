package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersProjectsRepository extends JpaRepository<UsersProjects, Long> {
    boolean existsByUserUserIdAndProjectProjectId(long userId, long projectId);
    UsersProjects findByUserUserIdAndProjectProjectId(long userId, long projectId);

/*
    // Select projects where the user has contributed at some point, i.e. it is in the table and the has_contributed flag is true
    @Query(value = "SELECT project_project_id FROM users_projects WHERE user_user_id = ?1 AND has_contributed = true", nativeQuery = true)
    Set<Long> findContributedProjectsByUserId(long userId);
*/
}
