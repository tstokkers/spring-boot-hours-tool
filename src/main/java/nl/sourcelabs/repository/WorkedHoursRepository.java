package nl.sourcelabs.repository;

import nl.sourcelabs.domain.User;
import nl.sourcelabs.domain.WorkedHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkedHoursRepository extends CrudRepository<WorkedHours, Long>{

    WorkedHours findWorkedHoursByUser(final User user);

}
