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

    /*
    *   `get_all_active_projects` is a database function with `?`s referring to the method parameters (similar to
    *   prepared statements in JDBC). You can find the corresponding SQL implementation in
    *   the src/main/resources/assets/postgre.sql.functions folder
    */
    @Query(value = "SELECT * FROM get_all_active_projects(?1, ?2, ?3, ?4)", nativeQuery = true)
    Set<Project> getAllActiveProjects(String industry, String status, String search, short limit);

    @Query(value = "SELECT * FROM get_user_unrelated_active_projects(?1)", nativeQuery = true)
    Set<Project> getUserUnrelatedActiveProjects(long userId);
}
