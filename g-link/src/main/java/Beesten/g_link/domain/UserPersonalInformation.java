package Beesten.g_link.domain;

import jakarta.persistence.*;
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
    private Float weight_kg;
    @NotNull
    private Float height_cm;
    @NotNull
    private LocalDate date_of_birth;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public UserPersonalInformation(Float weight_kg, Float height_cm, LocalDate date_of_birth,User user) {
        this.weight_kg = weight_kg;
        this.height_cm = height_cm;
        this.date_of_birth = date_of_birth;
        this.user = user;
    }

    public UserPersonalInformation() {

    }

    public Float getWeight_kg() {
        return weight_kg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWeight_kg(Float weight_kg) {
        this.weight_kg = weight_kg;
    }

    public Float getHeight_cm() {
        return height_cm;
    }

    public void setHeight_cm(Float height_cm) {
        this.height_cm = height_cm;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date_of_birth, currentDate);
        return period.getYears();
    }
}
