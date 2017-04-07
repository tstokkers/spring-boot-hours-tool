package nl.sourcelabs.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by tim on 6-4-2017.
 */
@Data
@Entity
public class WorkedHours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    private Double hoursWorked;
    private LocalDate workDate;

    public WorkedHours(User user, Double hoursWorked, LocalDate workDate) {
        this.user = user;
        this.hoursWorked = hoursWorked;
        this.workDate = workDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }
}
