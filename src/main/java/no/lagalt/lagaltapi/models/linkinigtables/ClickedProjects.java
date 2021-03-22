package no.lagalt.lagaltapi.models.linkinigtables;

import com.fasterxml.jackson.annotation.JsonFormat;
import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ClickedProjects {

    @EmbeddedId
    @Column(name = "users_projects_id")
    private UsersProjectsId id = new UsersProjectsId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @Column(name = "clicked_at")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Copenhagen")
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
