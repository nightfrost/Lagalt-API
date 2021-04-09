package no.lagalt.lagaltapi.models.modelHelpers;

import no.lagalt.lagaltapi.models.enums.ApprovalStatus;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;

/*
*   An interface that Hibernate uses to map the custom query results to a custom POJO
*   (cf. getUserRelatedProjectsByUserId in UserController and getUserRelatedProjectsByUserId in UserRepository)
*/

public interface UserRelatedProject {
    public boolean getAdmin();
    public long getProjectId();
    public String getProjectTitle();
    public ProjectType getProjectType();
    public ApprovalStatus getApprovalStatus();
    public ProjectProgress getProjectProgress();
    public boolean getProjectActive();
}
