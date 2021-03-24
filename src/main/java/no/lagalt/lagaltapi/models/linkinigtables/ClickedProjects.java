package no.lagalt.lagaltapi.models.linkinigtables;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;

import javax.persistence.*;
import java.sql.Timestamp;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@Entity
public class ClickedProjects {

    @EmbeddedId
    @Column(name = "users_projects_id")
    private UsersProjectsId id = new UsersProjectsId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @JsonGetter("user")
    public String userGetter() {
        if (user != null) {
            return BASE_URI_V1 + "users/" + user.getUserId();
        } else {
            return null;
        }
    }

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @JsonGetter("project")
    public String projectGetter() {
        if (project != null) {
            return BASE_URI_V1 + "projects/" + project.getProjectId();
        } else {
            return null;
        }
    }

    @Column(name = "clicked_at")
    private Timestamp clickedAt;

    public ClickedProjects() {
    }

    public ClickedProjects(User user, Project project, Timestamp clickedAt) {
        this.user = user;
        this.project = project;
        this.clickedAt = clickedAt;
    }

    public UsersProjectsId getId() {
        return id;
    }

    public ClickedProjects setId(UsersProjectsId id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ClickedProjects setUser(User user) {
        this.user = user;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public ClickedProjects setProject(Project project) {
        this.project = project;
        return this;
    }

    public Timestamp getClickedAt() {
        return clickedAt;
    }

    public ClickedProjects setClickedAt(Timestamp clickedAt) {
        this.clickedAt = clickedAt;
        return this;
    }
}
