package no.lagalt.lagaltapi.models.usersprojects;

import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;

import javax.persistence.*;

@Entity
public class UsersProjects {

    @EmbeddedId
    @Column(name = "users_projects_id")
    private UsersProjectsId id = new UsersProjectsId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(name = "motivation")
    private String motivation;

    @Column(name = "is_admin")
    private boolean isAdmin = false;
}
