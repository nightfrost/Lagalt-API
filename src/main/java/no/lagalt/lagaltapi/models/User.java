package no.lagalt.lagaltapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import no.lagalt.lagaltapi.models.linkinigtables.ClickedProjects;
import no.lagalt.lagaltapi.models.linkinigtables.UsersProjects;
import no.lagalt.lagaltapi.models.linkinigtables.ViewedProjects;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static no.lagalt.lagaltapi.controllers.ControllerHelper.BASE_URI_V1;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

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

    @JsonGetter("usersProjects")
    public List<String> usersProjectsGetter() {
        if (usersProjects != null) {
            return usersProjects.stream().map(usersProjects -> BASE_URI_V1 + "projects/" + usersProjects.getProject().getProjectId()).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<ClickedProjects> clickedProjects;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<ViewedProjects> viewedProjects;

    public User() {
    }

    public User(String userName, String userEmail, Set<String> userSkills, String userPortfolio,
                String userDescription, boolean userVisibility, Set<UsersProjects> usersProjects,
                Set<ClickedProjects> clickedProjects, Set<ViewedProjects> viewedProjects) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userSkills = userSkills;
        this.userPortfolio = userPortfolio;
        this.userDescription = userDescription;
        this.userVisibility = userVisibility;
        this.usersProjects = usersProjects;
        this.clickedProjects = clickedProjects;
        this.viewedProjects = viewedProjects;
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


