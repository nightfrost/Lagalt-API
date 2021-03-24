package no.lagalt.lagaltapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;
import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long projectId;

    @Column(name = "project_title", unique = true)
    private String projectTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_progress")
    private ProjectProgress projectProgress;

    @Column(name = "project_description")
    private String projectDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    private ProjectType projectType;

    @ElementCollection
    @CollectionTable(name="photos", joinColumns=@JoinColumn(name="project_id"))
    @Column(name = "project_photos")
    private Set<String> projectPhotos;

    @Column(name = "project_background_photo")
    private String projectBackgroundPhoto;

    @Column(name = "external_url")
    private String externalUrl;

    @ElementCollection
    @CollectionTable(name="project_skills", joinColumns=@JoinColumn(name="project_id"))
    @Column(name = "project_skills")
    private Set<String> projectSkills;

    @ElementCollection
    @CollectionTable(name="project_tags", joinColumns=@JoinColumn(name="project_id"))
    @Column(name = "project_tags")
    private Set<String> projectTags;

    @OneToMany
    @JoinColumn(name = "project_id")
    private Set<Announcement> announcements;

    @JsonGetter("announcements")
    public List<String> announcementsGetter() {
        if (announcements != null) {
            return announcements.stream().map(announcements -> BASE_URI_V1 + "announcements/" + announcements.getAnnouncementId()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @OneToMany
    @JoinColumn(name = "project_id")
    private Set<ProjectCard> projectCards;

    @JsonGetter("projectCards")
    public List<String> projectCardsGetter() {
        if (projectCards != null) {
            return projectCards.stream().map(projectCard -> BASE_URI_V1 + "projectCards/" + projectCard.getProjectCardId()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @OneToMany
    @JoinColumn(name = "project_project_id")
    private Set<UsersProjects> projectUsers;

    @JsonGetter("projectUsers")
    public List<String> projectUsersGetter() {
        if (projectUsers != null) {
            return projectUsers.stream().map(projectUsers -> BASE_URI_V1 + "users/" + projectUsers.getUser().getUserId()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @OneToMany
    @JoinColumn(name = "project_project_id")
    private Set<ClickedProjects> usersWhoClickedProject;

    @JsonGetter("usersWhoClickedProject")
    public List<String> clickedProjectsGetter() {
        if (usersWhoClickedProject != null) {
            return usersWhoClickedProject.stream().map(clickedProject -> BASE_URI_V1 + "users/" + clickedProject.getUser().getUserId()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @OneToMany
    @JoinColumn(name = "project_project_id")
    private Set<ViewedProjects> usersWhoViewedProject;

    @JsonGetter("usersWhoViewedProject")
    public List<String> viewedProjectsGetter() {
        if (usersWhoViewedProject != null) {
            return usersWhoViewedProject.stream().map(viewedProject -> BASE_URI_V1 + "users/" + viewedProject.getUser().getUserId()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public Project() {
    }

    public Project(String projectTitle, ProjectProgress projectProgress, ProjectType projectType,
                   Set<String> projectSkills, Set<String> projectTags) {
        this.projectTitle = projectTitle;
        this.projectProgress = projectProgress;
        this.projectType = projectType;
        this.projectSkills = projectSkills;
        this.projectTags = projectTags;
    }

    public long getProjectId() {
        return projectId;
    }

    public Project setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public Project setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
        return this;
    }

    public ProjectProgress getProjectProgress() {
        return projectProgress;
    }

    public Project setProjectProgress(ProjectProgress projectProgress) {
        this.projectProgress = projectProgress;
        return this;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Project setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public Project setProjectType(ProjectType projectType) {
        this.projectType = projectType;
        return this;
    }

    public Set<String> getProjectPhotos() {
        return projectPhotos;
    }

    public Project setProjectPhotos(Set<String> projectPhotos) {
        this.projectPhotos = projectPhotos;
        return this;
    }

    public String getProjectBackgroundPhoto() {
        return projectBackgroundPhoto;
    }

    public Project setProjectBackgroundPhoto(String projectBackgroundPhoto) {
        this.projectBackgroundPhoto = projectBackgroundPhoto;
        return this;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public Project setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
        return this;
    }

    public Set<String> getProjectSkills() {
        return projectSkills;
    }

    public Project setProjectSkills(Set<String> projectSkills) {
        this.projectSkills = projectSkills;
        return this;
    }

    public Set<String> getProjectTags() {
        return projectTags;
    }

    public Project setProjectTags(Set<String> projectTags) {
        this.projectTags = projectTags;
        return this;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public Project setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
        return this;
    }

    public Set<ProjectCard> getProjectCards() {
        return projectCards;
    }

    public Project setProjectCards(Set<ProjectCard> projectCards) {
        this.projectCards = projectCards;
        return this;
    }

    public Set<UsersProjects> getProjectUsers() {
        return projectUsers;
    }

    public Project setProjectUsers(Set<UsersProjects> projectUsers) {
        this.projectUsers = projectUsers;
        return this;
    }

    public Set<ClickedProjects> getUsersWhoClickedProject() {
        return usersWhoClickedProject;
    }

    public Project setUsersWhoClickedProject(Set<ClickedProjects> usersWhoClickedProject) {
        this.usersWhoClickedProject = usersWhoClickedProject;
        return this;
    }

    public Set<ViewedProjects> getUsersWhoViewedProject() {
        return usersWhoViewedProject;
    }

    public Project setUsersWhoViewedProject(Set<ViewedProjects> usersWhoViewedProject) {
        this.usersWhoViewedProject = usersWhoViewedProject;
        return this;
    }
}
