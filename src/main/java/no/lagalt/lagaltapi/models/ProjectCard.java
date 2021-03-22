package no.lagalt.lagaltapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.sql.Timestamp;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@Entity
@Table(name = "project_cards")
public class ProjectCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_card_id")
    private long projectCardId;

    @Column(name = "project_card_title")
    private String projectCardTitle;

    @Column(name = "project_card_text")
    private String projectCardText;

    @Column(name = "project_card_created_at")
    private Timestamp projectCardCreatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonGetter("project")
    public String projectGetter() {
        if (project != null) {
            return BASE_URI_V1 + "projects/" + project.getProjectId();
        } else {
            return null;
        }
    }

    public ProjectCard() {
    }

    public ProjectCard(String projectCardTitle, String projectCardText, Timestamp projectCardCreatedAt,
                       Project project) {
        this.projectCardTitle = projectCardTitle;
        this.projectCardText = projectCardText;
        this.projectCardCreatedAt = projectCardCreatedAt;
        this.project = project;
    }

    public long getProjectCardId() {
        return projectCardId;
    }

    public ProjectCard setProjectCardId(long projectCardId) {
        this.projectCardId = projectCardId;
        return this;
    }

    public String getProjectCardTitle() {
        return projectCardTitle;
    }

    public ProjectCard setProjectCardTitle(String projectCardTitle) {
        this.projectCardTitle = projectCardTitle;
        return this;
    }

    public String getProjectCardText() {
        return projectCardText;
    }

    public ProjectCard setProjectCardText(String projectCardText) {
        this.projectCardText = projectCardText;
        return this;
    }

    public Timestamp getProjectCardCreatedAt() {
        return projectCardCreatedAt;
    }

    public ProjectCard setProjectCardCreatedAt(Timestamp projectCardCreatedAt) {
        this.projectCardCreatedAt = projectCardCreatedAt;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public ProjectCard setProject(Project project) {
        this.project = project;
        return this;
    }
}
