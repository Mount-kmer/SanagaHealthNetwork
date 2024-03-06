package network.doctors.SanagaHealthNetwork.controllers;

//import jakarta.validation.Valid;
import network.doctors.SanagaHealthNetwork.convertors.TimeFormatter;
import network.doctors.SanagaHealthNetwork.entity.Appointment;
import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.model.AvailabilityForm;
import network.doctors.SanagaHealthNetwork.repositories.AppointmentRepository;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.service.AppointmentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class DoctorProfileController {

    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private  final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public DoctorProfileController(AppointmentService appointmentService,
                                   AppointmentRepository appointmentRepository,
                                   DoctorRepository doctorRepository) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/providerProfile")
    public String providerProfile(Model model, RedirectAttributes redirectAttributes) {
        logger.log(Level.INFO,"Going to provider profile");
        AvailabilityForm availabilityForm = new AvailabilityForm();
        List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();
        model.addAttribute("appointment", appointments);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        DoctorList doctor = doctorRepository.searchByEmail(name);

        if (doctor != null){
            String firstname = doctor.getFirstName();
            String lastname = doctor.getLastName();
            int doctorId = doctor.getId();
            model.addAttribute("name", firstname + " " + lastname);
        }


        if (redirectAttributes.getFlashAttributes().containsKey("availabilitySet")) {
            String availabilitySet = (String) redirectAttributes.getFlashAttributes().get("availabilitySet");
            model.addAttribute("availabilitySet", availabilitySet);
        }
        model.addAttribute("availability", availabilityForm);
        return "DoctorProfile";
    }

    @PostMapping("/setAvailability")
    public String setAvailability(@Valid @ModelAttribute("availability")AvailabilityForm availabilityForm,
                                  Model model, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors())
            return "error";

        LocalTime startTime = TimeFormatter.formatTime(availabilityForm.getStartTime().toString().trim());
        LocalTime endTime = TimeFormatter.formatTime(availabilityForm.getEndTime().toString().trim());

        appointmentService.createDoctorAvailability(availabilityForm.getLocalDate(), startTime,
                endTime);
        String successMessage =  "You have set your availability for the following:" +
                availabilityForm.getLocalDate() + ", " + availabilityForm.getStartTime() +
                ", " + availabilityForm.getEndTime();
        redirectAttributes.addFlashAttribute("availability", successMessage);
        return "redirect:/providerProfile";
    }

    @GetMapping("/provider_logout")
    public ModelAndView logout(HttpSession session){
        logger.log(Level.INFO, "Logging out user and ending session "+ session.getAttributeNames());
        session.invalidate();
        return new ModelAndView("redirect:/provider_login_form");

    }

}
