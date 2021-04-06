package no.lagalt.lagaltapi.models.modelHelpers;

import no.lagalt.lagaltapi.models.enums.ApprovalStatus;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;

public interface UserRelatedProject {
    public boolean getAdmin();
    public long getProjectId();
    public String getProjectTitle();
    public ProjectType getProjectType();
    public ApprovalStatus getApprovalStatus();
    public ProjectProgress getProjectProgress();
    public boolean getProjectActive();
}
