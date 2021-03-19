package no.lagalt.lagaltapi.models;

import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @ElementCollection
    @CollectionTable(name="user_skills", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="user_skills")
    private Set<String> userSkills;

    @Column(name = "user_portfolio")
    private String userPortfolio;

    @Column(name = "user_description")
    private String userDescription;

    @Column(name = "user_visibility")
    private boolean userVisibility;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<UsersProjects> usersProjects;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<ClickedProjects> clickedProjects;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<ViewedProjects> viewedProjects;

    public User() {
    }

    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userVisibility = false;
    }

    public long getUserId() {
        return userId;
    }

    public User setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public User setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Set<String> getUserSkills() {
        return userSkills;
    }

    public User setUserSkills(Set<String> userSkills) {
        this.userSkills = userSkills;
        return this;
    }

    public String getUserPortfolio() {
        return userPortfolio;
    }

    public User setUserPortfolio(String userPortfolio) {
        this.userPortfolio = userPortfolio;
        return this;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public User setUserDescription(String userDescription) {
        this.userDescription = userDescription;
        return this;
    }

    public boolean isUserVisibility() {
        return userVisibility;
    }

    public User setUserVisibility(boolean userVisibility) {
        this.userVisibility = userVisibility;
        return this;
    }
}


