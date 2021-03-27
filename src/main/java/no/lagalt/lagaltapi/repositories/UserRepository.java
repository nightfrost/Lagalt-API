package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT DISTINCT project_id FROM public.user_skills JOIN public.project_skills ON(user_skills = project_skills) WHERE user_id = ?1", nativeQuery = true)
    Set<Long> findUserProjectMatchingSkillsByUserId(long userId);

    @Query(value = "SELECT get_suggestions_from_contributed_projects(?1)", nativeQuery = true)
    Set<Long> getSuggestionsFromContributedProjects(long userId);
}
