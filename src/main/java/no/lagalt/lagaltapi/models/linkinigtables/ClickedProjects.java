package no.lagalt.lagaltapi.models.linkinigtables;

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
    private Timestamp clickedAt;
}
