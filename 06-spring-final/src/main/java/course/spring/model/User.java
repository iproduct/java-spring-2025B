package course.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static course.spring.model.Role.READER;

@Entity
//@Table(name="USERS", uniqueConstraints = {
//        @UniqueConstraint(name="UC_USERNAME", columnNames={"USERNAME"}),
//        @UniqueConstraint(name="UC_FIST_LAST_NAME", columnNames={"FIRST_NAME", "LAST_NAME"})
//})
@Table(name="USERS", indexes = {
        @Index(name = "UC_USERNAME", columnList = "USERNAME", unique = true),
        @Index(name = "UC_NAMES", columnList = "FIRST_NAME,LAST_NAME",  unique = true),
})
public class User extends Person {
    @Basic(optional = false)
    @Column( nullable = false, updatable = false, length = 30)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Role role = READER;
    @Basic(optional = false)
    @Column( nullable = false, length = 80)
    private String email;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Article> articlesAuthored = new ArrayList<>();
    @OneToMany(mappedBy = "editor")
    @JsonIgnore
    private List<Article> articlesEdited = new ArrayList<>();;

    public User() {
        super();
    }

    public User(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String username, String password, Role role, String email) {
        super(firstName, lastName, birthDate);
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(Long id, String firstName, String lastName, LocalDate birthDate, String username, String password, Role role, String email) {
        super(id, firstName, lastName, birthDate);
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Article> getArticlesAuthored() {
        return articlesAuthored;
    }

    public void setArticlesAuthored(List<Article> articlesAuthored) {
        this.articlesAuthored = articlesAuthored;
    }

    public List<Article> getArticlesEdited() {
        return articlesEdited;
    }

    public void setArticlesEdited(List<Article> articlesEdited) {
        this.articlesEdited = articlesEdited;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", birthDate='" + getBirthDate() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                "} ";
    }
}
