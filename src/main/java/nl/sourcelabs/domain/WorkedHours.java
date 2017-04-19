package nl.sourcelabs.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by tim on 6-4-2017.
 */
@Entity
public class WorkedHours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double hoursWorked;
    private LocalDate workDate;
    private String username;

    public WorkedHours(String username, Double hoursWorked, LocalDate workDate) {
        this.username = username;
        this.hoursWorked = hoursWorked;
        this.workDate = workDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername(){return username;}

    public void setUsername(String username) {
        this.username = username;
    }
}
