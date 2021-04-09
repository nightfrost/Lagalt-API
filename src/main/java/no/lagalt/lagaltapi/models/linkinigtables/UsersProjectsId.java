package no.lagalt.lagaltapi.models.linkinigtables;

import java.io.Serializable;
import java.util.Objects;

/*
*   UsersProjectsId class implements a composite key generator for the linking tables. It is a way to have additional
*   columns in the linking tables, which is not supported with Hibernate Many-to-Many relation mapping
*/

public class UsersProjectsId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long projectId;

    public UsersProjectsId() {
    }

    public UsersProjectsId(long userId, long projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public UsersProjectsId setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getProjectId() {
        return projectId;
    }

    public UsersProjectsId setProjectId(long projectId) {
        this.projectId = projectId;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result
                + ((projectId == null) ? 0 : projectId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsersProjectsId other = (UsersProjectsId) obj;
        return Objects.equals(getUserId(), other.getUserId()) && Objects.equals(getProjectId(), other.getProjectId());
    }
}
