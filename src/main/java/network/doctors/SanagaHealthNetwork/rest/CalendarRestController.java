package network.doctors.SanagaHealthNetwork.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.TimePeriod;
import network.doctors.SanagaHealthNetwork.service.GoogleCalendarService;
import network.doctors.SanagaHealthNetwork.utility.JSonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CalendarRestController {

    private final Logger logger = Logger.getLogger((CalendarRestController.class.getName()));
    private final GoogleCalendarService googleCalendarService;


    private final JSonUtil jSonUtil;

    public CalendarRestController(GoogleCalendarService googleCalendarService, JSonUtil jSonUtil) {
        this.googleCalendarService = googleCalendarService;
        this.jSonUtil = jSonUtil;
    }

    @GetMapping(value = "/calendar-events", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> openCalendarSlots(@RequestParam("date") String selectedDate, Model model) throws GeneralSecurityException, IOException {

        if (selectedDate == null || selectedDate.isEmpty()) {
            return ResponseEntity.badRequest().body("Date param is missing");
        }

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate userSelectedDate = LocalDate.parse(selectedDate, formatter);

            LocalDateTime startDateTime = userSelectedDate.atTime(8,0);
            LocalDateTime endDateTime = userSelectedDate.atTime(17,0);

            ZoneId zoneId = ZoneId.systemDefault();

            DateTime startTime = new DateTime(startDateTime.atZone(zoneId).toInstant().toEpochMilli());
            DateTime endTime = new DateTime(endDateTime.atZone(zoneId).toInstant().toEpochMilli());

            List<TimePeriod> slots = googleCalendarService.getTimeSlots("daniel.mbouzou@gmail.com", startTime, endTime);
            List<LocalTime> openSlots = googleCalendarService.calculateFreeSlots(slots, startTime, endTime, Duration.ofHours(1));
            logger.log(Level.INFO,"OpenSlots:::: --> " + openSlots);
            logger.log(Level.INFO, "slots" + slots);
            model.addAttribute("selectedDate", selectedDate);

            return ResponseEntity.ok(jSonUtil.convertToJson(openSlots));

        }catch (GeneralSecurityException e) {
            logger.log(Level.SEVERE, "Error getting calendar info: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching calendar slots");

        }

    }

    public String convertToJson(Object data) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.writeValueAsString(data);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error Converting data to JSON" + " " + e);
            return "{}";
        }
    }

}
