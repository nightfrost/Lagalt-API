package no.lagalt.lagaltapi.models.modelHelpers;

import no.lagalt.lagaltapi.models.User;

public class UserUpdate {
    private UserId userId;
    private User user;

    public UserId getUserId() {
        return userId;
    }

    public UserUpdate setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserUpdate setUser(User user) {
        this.user = user;
        return this;
    }
}
