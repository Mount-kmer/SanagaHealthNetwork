package network.doctors.SanagaHealthNetwork.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import network.doctors.SanagaHealthNetwork.service.GoogleCalendarService;
import network.doctors.SanagaHealthNetwork.utility.JSonUtil;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class CalendarController {

    private final GoogleCalendarService googleCalendarService;
    private final Logger logger = Logger.getLogger((CalendarController.class.getName()));
    private JSonUtil jSonUtil;

    public CalendarController(GoogleCalendarService googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
    }

//    @GetMapping("/calendar-events")
//    public String getCalendar(Model modelAttribute) throws GeneralSecurityException, IOException {
////        Calendar.Events calendar = (Calendar.Events) googleCalendarService.getEvents();
////        DateTime now = new DateTime(System.currentTimeMillis());
////        LocalDate date = LocalDate.of(2024,01,12);
////        DateTime start = new DateTime(date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
////        DateTime t = new DateTime(date.atTime(8,0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
////        logger.log(Level.INFO, "TimeZOne: " + start);
////        logger.log(Level.INFO, "CheckVAL: " + t);
////
////        List<Event> events = googleCalendarService.getEvents();
//
//        DateTime startTime = new DateTime("2024-01-12T08:00:00-06:00");
//        DateTime endTime = new DateTime("2024-01-12T17:00:00-06:00");
//
//        List<TimePeriod> slots = googleCalendarService.getTimeSlots("daniel.mbouzou@gmail.com", startTime, endTime);
//        List<LocalTime> openSlots = googleCalendarService.calculateFreeSlots(slots, startTime, endTime, Duration.ofHours(1));
//        logger.log(Level.INFO,"OpenSlots:::: --> " + openSlots);
////        logger.log(Level.INFO, "freeOpenedSlots" + newSlots);
//        logger.log(Level.INFO, "slots" + slots);
////        logger.log(Level.INFO,"events" + events);
//
//
////        modelAttribute.addAttribute("events", events);
//        modelAttribute.addAttribute("OpenTimeSlots", openSlots);
//        modelAttribute.addAttribute("timeslots", slots);
//
//
//
//        return "appointment";
//
//    }
//
//    @GetMapping("/calendar-events")
//    public String getCalendar(Model modelAttribute, HttpServletRequest request, HttpServletResponse response) throws GeneralSecurityException, IOException {
//
//        String selectedDate = request.getParameter("date");
//
//        if (!selectedDate.isEmpty()) {
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate userSelectedDate = LocalDate.parse(selectedDate, formatter);
//
//            LocalDateTime startDateTime = userSelectedDate.atTime(8,0);
//            LocalDateTime endDateTime = userSelectedDate.atTime(17,0);
//
//            ZoneId zoneId = ZoneId.systemDefault();
//
//            DateTime startTime = new DateTime(startDateTime.atZone(zoneId).toInstant().toEpochMilli());
//            DateTime endTime = new DateTime(endDateTime.atZone(zoneId).toInstant().toEpochMilli());
//
//            List<TimePeriod> slots = googleCalendarService.getTimeSlots("daniel.mbouzou@gmail.com", startTime, endTime);
//            List<LocalTime> openSlots = googleCalendarService.calculateFreeSlots(slots, startTime, endTime, Duration.ofHours(1));
//            logger.log(Level.INFO,"OpenSlots:::: --> " + openSlots);
//            logger.log(Level.INFO, "slots" + slots);
//
//            modelAttribute.addAttribute("OpenTimeSlots", openSlots);
//            modelAttribute.addAttribute("timeslots", slots);
//            modelAttribute.addAttribute("selectedDate", selectedDate);
//
//            return "appointment";
//
//        } else {
//            return "error";
//        }
//
//    }

//
//    @GetMapping(value = "/calendar-events", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getCalendarSlots(@RequestParam("date") String selectedDate) throws GeneralSecurityException, IOException {
//        if (!selectedDate.isEmpty()) {
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate userSelectedDate = LocalDate.parse(selectedDate, formatter);
//
//            LocalDateTime startDateTime = userSelectedDate.atTime(8,0);
//            LocalDateTime endDateTime = userSelectedDate.atTime(17,0);
//
//            ZoneId zoneId = ZoneId.systemDefault();
//
//            DateTime startTime = new DateTime(startDateTime.atZone(zoneId).toInstant().toEpochMilli());
//            DateTime endTime = new DateTime(endDateTime.atZone(zoneId).toInstant().toEpochMilli());
//
//            List<TimePeriod> slots = googleCalendarService.getTimeSlots("daniel.mbouzou@gmail.com", startTime, endTime);
//            List<LocalTime> openSlots = googleCalendarService.calculateFreeSlots(slots, startTime, endTime, Duration.ofHours(1));
//            logger.log(Level.INFO,"OpenSlots:::: --> " + openSlots);
//            logger.log(Level.INFO, "slots" + slots);
//
//            return convertToJson(openSlots);
//        }
//
//        return "error";
//    }

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
