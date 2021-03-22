package no.lagalt.lagaltapi.models.linkinigtables;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ViewedProjects {

    @EmbeddedId
    @Column(name = "users_projects_id")
    private UsersProjectsId id = new UsersProjectsId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @Column(name = "viewed_at")
    private Timestamp viewedAt;

    public ViewedProjects() {
    }

    public ViewedProjects(User user, Project project, Timestamp viewedAt) {
        this.user = user;
        this.project = project;
        this.viewedAt = viewedAt;
    }

    public UsersProjectsId getId() {
        return id;
    }

    public ViewedProjects setId(UsersProjectsId id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ViewedProjects setUser(User user) {
        this.user = user;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public ViewedProjects setProject(Project project) {
        this.project = project;
        return this;
    }

    public Timestamp getViewedAt() {
        return viewedAt;
    }

    public ViewedProjects setViewedAt(Timestamp viewedAt) {
        this.viewedAt = viewedAt;
        return this;
    }
}
