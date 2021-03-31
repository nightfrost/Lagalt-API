package no.lagalt.lagaltapi.repositories;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT EXISTS(SELECT * FROM projects WHERE is_active = true)", nativeQuery = true)
    boolean existsActiveProjects();

    @Query(value = "SELECT * FROM get_search_results(?1, ?2, ?3, ?4)", nativeQuery = true)
    Set<Project> getAllActiveProjects(String industry, String status, String search, short limit);
}
