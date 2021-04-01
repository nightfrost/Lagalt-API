package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.User;
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
}
