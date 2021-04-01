package no.lagalt.lagaltapi.models.modelHelpers;

public class UserId {
    private long userId;

    public UserId() {
    }

    public UserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public UserId setUserId(long userId) {
        this.userId = userId;
        return this;
    }
}
