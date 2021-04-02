package no.lagalt.lagaltapi.models.linkinigtables;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.lagalt.lagaltapi.models.Project;
import no.lagalt.lagaltapi.models.User;
import no.lagalt.lagaltapi.models.enums.ApprovalStatus;

import javax.persistence.*;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@Entity
public class UsersProjects {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Column(name = "has_contributed")
    private boolean hasContributed = false;

    @Column(name = "motivation")
    private String motivation;

    @Column(name = "is_admin")
    private boolean isAdmin = false;

    public UsersProjects() {
    }

    public UsersProjects(User user, Project project, String motivation) {
        this.user = user;
        this.project = project;
        this.motivation = motivation;
    }

    public UsersProjectsId getId() {
        return id;
    }

    public UsersProjects setId(UsersProjectsId id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UsersProjects setUser(User user) {
        this.user = user;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public UsersProjects setProject(Project project) {
        this.project = project;
        return this;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public UsersProjects setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
        return this;
    }

    public boolean isHasContributed() {
        return hasContributed;
    }

    public UsersProjects setHasContributed(boolean hasContributed) {
        this.hasContributed = hasContributed;
        return this;
    }

    public String getMotivation() {
        return motivation;
    }

    public UsersProjects setMotivation(String motivation) {
        this.motivation = motivation;
        return this;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public UsersProjects setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }
}
