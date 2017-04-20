package nl.sourcelabs.repository;

import nl.sourcelabs.domain.WorkedHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkedHoursRepository extends CrudRepository<WorkedHours, Long>{

    List<WorkedHours> findWorkedHoursByUsernameOrderByWorkDateAsc(final String username);

    List<WorkedHours> findAllByWorkDateNotNull();

    WorkedHours findById(final long id);

}
