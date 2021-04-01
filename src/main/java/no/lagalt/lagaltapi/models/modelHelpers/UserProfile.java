package no.lagalt.lagaltapi.models.modelHelpers;

import no.lagalt.lagaltapi.models.enums.ApprovalStatus;
import no.lagalt.lagaltapi.models.enums.ProjectProgress;
import no.lagalt.lagaltapi.models.enums.ProjectType;

public class UserProfile {
    private boolean isAdmin;
    private String projectTitle;
    private ProjectType projectType;
    private ApprovalStatus approvalStatus;
    private ProjectProgress projectProgress;
    private boolean isProjectActive;
}
