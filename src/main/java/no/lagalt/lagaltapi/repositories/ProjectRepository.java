package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByProjectTitle(String projectTitle);

    @Query(value = "SELECT EXISTS(SELECT * FROM projects WHERE is_active = true)", nativeQuery = true)
    boolean existsActiveProjects();

    @Query(value = "SELECT * FROM get_all_active_projects(?1, ?2, ?3, ?4)", nativeQuery = true)
    Set<Project> getAllActiveProjects(String industry, String status, String search, short limit);
}
