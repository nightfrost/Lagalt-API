package no.lagalt.lagaltapi.models.modelHelpers;

import no.lagalt.lagaltapi.models.Project;

public class ProjectUpdate {
    private UserId userId;
    private Project project;

    public UserId getUserId() {
        return userId;
    }

    public ProjectUpdate setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public ProjectUpdate setProject(Project project) {
        this.project = project;
        return this;
    }
}
