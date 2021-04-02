package no.lagalt.lagaltapi.models.modelHelpers;

import no.lagalt.lagaltapi.models.enums.ApprovalStatus;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;

/*
@SqlResultSetMapping(name = "UserRelatedProjectMapping",
        classes = @ConstructorResult(
                targetClass = UserRelatedProject.class,
                columns = {@ColumnResult(name = "isAdmin"),
                        @ColumnResult(name = "projectId"),
                        @ColumnResult(name = "projectTitle"),
                        @ColumnResult(name = "projectType"),
                        @ColumnResult(name = "approvalStatus"),
                        @ColumnResult(name = "projectProgress"),
                        @ColumnResult(name = "isProjectActive")
                }
        )
)
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
