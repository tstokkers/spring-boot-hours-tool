package nl.sourcelabs.controller;

import nl.sourcelabs.domain.User;
import nl.sourcelabs.domain.WorkedHours;
import nl.sourcelabs.repository.UserRepository;
import nl.sourcelabs.repository.WorkedHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@EnableWebMvc
public class WorkedHoursController {

    @Autowired
    WorkedHoursRepository workedHoursRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/add")
    public ResponseEntity add(@RequestParam("username") String username, @RequestParam("hours") String hours, @RequestParam("date") String date, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepository.findUserByUsername(username);
        if (user == null || hours.isEmpty() || date.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        LocalDate workDate;
        try {
            workDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        WorkedHours workedHours = new WorkedHours(username, Double.parseDouble(hours), workDate);
        workedHoursRepository.save(workedHours);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
