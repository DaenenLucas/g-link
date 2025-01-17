package Beesten.g_link.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class UserPersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String job;
    @NotNull
    private LocalDate date_of_birth;
    @NotNull
    private String function;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserProfile userProfile;

    public UserPersonalInformation(String job, LocalDate date_of_birth, UserProfile userProfile, String function) {
        this.job = job;
        this.date_of_birth = date_of_birth;
        this.userProfile = userProfile;
        this.function = function;
    }




    public UserPersonalInformation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getUser() {
        return userProfile;
    }

    public void setUser(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date_of_birth, currentDate);
        return period.getYears();
    }
}
