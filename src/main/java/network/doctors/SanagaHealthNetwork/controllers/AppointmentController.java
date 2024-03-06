package network.doctors.SanagaHealthNetwork.controllers;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.model.AppointmentForm;
import network.doctors.SanagaHealthNetwork.repositories.AppointmentRepository;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.AppointmentService;
import network.doctors.SanagaHealthNetwork.service.GoogleCalendarService;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaUserDetailsService;
import network.doctors.SanagaHealthNetwork.utility.JSonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class AppointmentController {

    private final DoctorRepository doctorRepository;

    private final AppointmentService appointmentService;

    private final SanagaUserDetailsService service;

    private final UserRepository userRepository;
    private static Logger logger;

    private final AppointmentRepository appointmentRepository;

    private final GoogleCalendarService googleCalendarService;

    private JSonUtil jSonUtil;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Value("classpath:static/json/client_secret_2_476488849566-artu7pq5snfq4l6ahtdsrt0l1sjnntl6.apps.googleusercontent.com.json")
    private Resource credentialResource;


    public AppointmentController(DoctorRepository doctorRepository, AppointmentService appointmentService, SanagaUserDetailsService service, UserRepository userRepository, AppointmentRepository appointmentRepository, GoogleCalendarService googleCalendarService) {
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
        this.service = service;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.googleCalendarService = googleCalendarService;
    }

    @GetMapping("/appointment/**")
    public String presentAppointmentForm(
            @RequestParam("name") String lastname,
            @RequestParam("id") int id, Model model) throws GeneralSecurityException, IOException {
        List<DoctorList> doctorsList = doctorRepository.findByLastName(lastname);
        model.addAttribute("doctorInfo", doctorsList);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("doctorName", lastname);
        model.addAttribute("doctorId", id);
        AppointmentForm appointmentForm = new AppointmentForm();
        model.addAttribute("appointment", appointmentForm);

        return "appointment";
    }



    @PostMapping("/appointments/new")
    public String submitAppointmentForm(
            Model model, @Valid @ModelAttribute("appointment") AppointmentForm appointment, BindingResult result,
            @RequestParam("doctorName") String name, @RequestParam("id") Integer id
    ) throws ParseException, IOException, GeneralSecurityException {

        System.out.println("Recieved "+ appointment.getTime());
        System.out.println("Recieved "+ appointment.getDate());


        if (result.hasErrors()) {
            model.addAttribute("invalidDate", "please select a date that is not in the past");
            return "appointment";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = auth.getName();
        User user = userRepository.searchByName(loggedUser);

        if (user != null) {
            int loggedInUserId = user.getId();
            String appDate = appointmentService.formatDate(appointment.getDate());

            String time = appointment.getTime();

            LocalDate date = appointment.getDate();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime time1 = LocalTime.parse(time, formatter);

            LocalDateTime startTime = LocalDateTime.of(date, time1);

            ZonedDateTime zonedDateTimeStart = startTime.atZone(ZoneId.of("America/Chicago"));
            ZonedDateTime zonedDateTimeEnd = zonedDateTimeStart.plus(Duration.ofHours(1));
            String startDateTimeRFC = zonedDateTimeStart.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            String endDateTimeRFC = zonedDateTimeEnd.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            String patientName = user.getFirstName()  + user.getFirstName();

            DoctorList doctorList = doctorRepository.searchByName(name);

            googleCalendarService.createCalendarEvent(doctorList.getHospitalWorked(), "daniel.mbouzou@gmail.com",
                    startDateTimeRFC, endDateTimeRFC, patientName );
            appointmentService.createAppointment(id,loggedInUserId, appointment.getTime(), appointment.getDate());
            model.addAttribute("bookedAppointment", "You have successfully booked an appointment with Dr. "
                    + name + " on" + appDate+ " " );
            model.addAttribute("id", user);
            return "appointment_success";
        }
        return "error";

    }



    public void formatRFCDate(String time, LocalDate date) {
        LocalDateTime startTime = LocalDateTime.parse(date + "T" + time + ":00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ZonedDateTime zonedDateTimeStart = startTime.atZone(ZoneId.of("America/Chicago"));
        ZonedDateTime zonedDateTimeEnd = zonedDateTimeStart.plus(Duration.ofHours(1));
        String startDateTimeRFC = zonedDateTimeStart.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String endDateTimeRFC = zonedDateTimeEnd.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    }
}
