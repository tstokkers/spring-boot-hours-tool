package nl.sourcelabs.repository;

import nl.sourcelabs.domain.WorkedHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tim on 7-4-2017.
 */
@Repository
public interface WorkedHoursRepository extends CrudRepository<WorkedHours, Long>{


}
