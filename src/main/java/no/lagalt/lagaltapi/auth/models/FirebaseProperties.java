package no.lagalt.lagaltapi.auth.models;

public class FirebaseProperties {
    int sessionExpiryInDays;
    String databaseUrl;
    boolean enableStrictServerSession;
    boolean enableCheckSessionRevoked;
    boolean enableLogoutEverywhere;

    public FirebaseProperties(int sessionExpiryInDays, String databaseUrl, boolean enableStrictServerSession, boolean enableCheckSessionRevoked, boolean enableLogoutEverywhere) {
        this.sessionExpiryInDays = sessionExpiryInDays;
        this.databaseUrl = databaseUrl;
        this.enableStrictServerSession = enableStrictServerSession;
        this.enableCheckSessionRevoked = enableCheckSessionRevoked;
        this.enableLogoutEverywhere = enableLogoutEverywhere;
    }

    public FirebaseProperties() {
    }

    public int getSessionExpiryInDays() {
        return sessionExpiryInDays;
    }

    public void setSessionExpiryInDays(int sessionExpiryInDays) {
        this.sessionExpiryInDays = sessionExpiryInDays;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public boolean isEnableStrictServerSession() {
        return enableStrictServerSession;
    }

    public void setEnableStrictServerSession(boolean enableStrictServerSession) {
        this.enableStrictServerSession = enableStrictServerSession;
    }

    public boolean isEnableCheckSessionRevoked() {
        return enableCheckSessionRevoked;
    }

    public void setEnableCheckSessionRevoked(boolean enableCheckSessionRevoked) {
        this.enableCheckSessionRevoked = enableCheckSessionRevoked;
    }

    public boolean isEnableLogoutEverywhere() {
        return enableLogoutEverywhere;
    }

    public void setEnableLogoutEverywhere(boolean enableLogoutEverywhere) {
        this.enableLogoutEverywhere = enableLogoutEverywhere;
    }
}