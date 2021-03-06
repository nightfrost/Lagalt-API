package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.modelHelpers.UserRelatedProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);

    @Query(value = "SELECT get_suggestions_from_contributed_projects(?1)", nativeQuery = true)
    Set<Long> getSuggestionsFromContributedProjects(long userId);

    @Query(value = "SELECT get_suggestions_from_clicked_projects(?1)", nativeQuery = true)
    Set<Long> getSuggestionsFromClickedProjects(long userId);

    @Query(value = "SELECT get_suggestions_from_user_skills(?1)", nativeQuery = true)
    Set<Long> findUserProjectMatchingSkillsByUserId(long userId);

    @Query(value = "SELECT get_suggestions_from_viewed_projects(?1)", nativeQuery = true)
    Set<Long> getSuggestionsFromViewedProjects(long userId);

    // Custom query function to fetch data on user related projects from multiple tables
    @Query(value = "SELECT p.is_admin AS admin, p.project_id AS projectId, p.project_title AS projectTitle, p.project_type AS projectType, p.approval_status AS approvalStatus, p.project_progress AS projectProgress, p.is_active AS projectActive FROM (SELECT * FROM get_user_related_projects(?1)) p", nativeQuery = true)
    Set<UserRelatedProject> getUserRelatedProjectsByUserId(long userId);
}
