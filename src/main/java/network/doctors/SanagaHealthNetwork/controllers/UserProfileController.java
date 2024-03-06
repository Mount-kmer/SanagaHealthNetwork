package network.doctors.SanagaHealthNetwork.controllers;

import network.doctors.SanagaHealthNetwork.convertors.DateFormatter;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.model.Login;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.impl.AppointmentServiceImplementation;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.logging.Logger;

@Controller
public class UserProfileController {

    @Autowired
    private DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final SanagaUserDetailsService service;
    private final AppointmentServiceImplementation appointmentServiceImplementation;

    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    public UserProfileController(UserRepository userRepository, SanagaUserDetailsService service, AppointmentServiceImplementation appointmentServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
        ;
        this.userRepository = userRepository;
        this.service = service;
    }

    @GetMapping("/userprofile/**")
    public String returnProfile(@ModelAttribute("login") Login login, Model model) {
        model.addAttribute("hello","greetings user");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userDetails =  userRepository.searchByName(username);
        DateFormatter dateFormatter = new DateFormatter();

        if (userDetails != null) {
            String firstname = userDetails.getFirstName();
            String lastName = userDetails.getLastName();
            int id = userDetails.getId();
            model.addAttribute("app", appointmentServiceImplementation.getAppointmentByUserId(id));
            model.addAttribute("firstname",firstname );
            model.addAttribute("lastname", lastName);
            model.addAttribute("userId", id);
            model.addAttribute("format", dateFormatter);
        }

        return "profile";
    }


//    @RequestMapping(value = "/{path:[^\\.]*}")
//    public String redirect() {
//        return "redirect:profile";
//    }

}
