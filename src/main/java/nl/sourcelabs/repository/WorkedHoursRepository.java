package nl.sourcelabs.repository;

import nl.sourcelabs.domain.WorkedHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface WorkedHoursRepository extends CrudRepository<WorkedHours, Long>{

    @GetMapping
    List<WorkedHours> findWorkedHoursByUsernameOrderByWorkDateAsc(@Param("username") final String username);

}
