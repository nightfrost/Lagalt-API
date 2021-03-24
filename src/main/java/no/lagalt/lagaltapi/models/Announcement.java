package no.lagalt.lagaltapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.sql.Timestamp;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private long announcementId;

    @Column(name = "announcement_title")
    private String announcementTitle;

    @Column(name = "announcement_text")
    private String announcementText;

    @Column(name = "announcement_created_at")
    private Timestamp announcementCreatedAt;

    @Column(name = "announcement_updated_at")
    private Timestamp announcementUpdatedAt;

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

    public Announcement() {
    }

    public Announcement(String announcementTitle, String announcementText, Timestamp announcementCreatedAt, Project project) {
        this.announcementTitle = announcementTitle;
        this.announcementText = announcementText;
        this.announcementCreatedAt = announcementCreatedAt;
        this.announcementUpdatedAt = null;
        this.project = project;
    }

    public long getAnnouncementId() {
        return announcementId;
    }

    public Announcement setAnnouncementId(long announcementId) {
        this.announcementId = announcementId;
        return this;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public Announcement setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
        return this;
    }

    public String getAnnouncementText() {
        return announcementText;
    }

    public Announcement setAnnouncementText(String announcementText) {
        this.announcementText = announcementText;
        return this;
    }

    public Timestamp getAnnouncementCreatedAt() {
        return announcementCreatedAt;
    }

    public Announcement setAnnouncementCreatedAt(Timestamp announcementCreatedAt) {
        this.announcementCreatedAt = announcementCreatedAt;
        return this;
    }

    public Timestamp getAnnouncementUpdatedAt() {
        return announcementUpdatedAt;
    }

    public Announcement setAnnouncementUpdatedAt(Timestamp announcementUpdatedAt) {
        this.announcementUpdatedAt = announcementUpdatedAt;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Announcement setProject(Project project) {
        this.project = project;
        return this;
    }
}
