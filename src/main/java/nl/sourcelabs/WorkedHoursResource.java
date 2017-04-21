package nl.sourcelabs;

import nl.sourcelabs.domain.User;
import nl.sourcelabs.domain.WorkedHours;
import nl.sourcelabs.repository.UserRepository;
import nl.sourcelabs.repository.WorkedHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WorkedHoursResource {

    @Autowired
    WorkedHoursRepository workedHoursRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/worked-hours")
    public ResponseEntity<List<WorkedHours>> getWorkedHours() {
        List<WorkedHours> workedHoursList = new ArrayList<>();
        return ResponseEntity.ok(workedHoursRepository.findAllByWorkDateNotNull());
    }

    @GetMapping("/worked-hours/{id}")
    public ResponseEntity<WorkedHours> getWorkedHoursById(@PathVariable final long id) {
        return ResponseEntity.ok(workedHoursRepository.findById(id));
    }

    @PostMapping("/worked-hours")
    public ResponseEntity addWorkedHours(@RequestBody final WorkedHours workedHours) throws Exception {
        User user = userRepository.findUserByUsername(workedHours.getUsername());
        if (user != null) {
            workedHoursRepository.save(workedHours);
            return ResponseEntity.created(new URI("/worked-hours/" + workedHours.getId())).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/worked-hours/{id}")
    public ResponseEntity updateWorkedHours(@PathVariable final long id, @RequestBody final WorkedHours workedHours) throws Exception {
        WorkedHours existingWorkedHours = workedHoursRepository.findById(id);
        if (existingWorkedHours != null) {
            existingWorkedHours.setUsername(workedHours.getUsername());
            existingWorkedHours.setHoursWorked(workedHours.getHoursWorked());
            existingWorkedHours.setWorkDate(workedHours.getWorkDate());
            workedHoursRepository.save(existingWorkedHours);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("worked-hours/{id}")
    public ResponseEntity deleteWorkedHours(@PathVariable final long id) throws Exception {
        WorkedHours workedHours = workedHoursRepository.findById(id);
        if (workedHours != null) {
            workedHoursRepository.delete(workedHours);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("worked-hours/{week}")
    public ResponseEntity<List<WorkedHours>> getWorkedHoursForWeeknumber(@PathVariable final int week) {
        List<WorkedHours> allWorkedHours = workedHoursRepository.findAllByWorkDateNotNull();
        List<WorkedHours> resultList = allWorkedHours.stream().filter(wh -> wh.getWeeknumber() == week).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "Something went wrong.")
    @ExceptionHandler(Exception.class)
    public void handleError() {
        // Nothing to do
    }
}
