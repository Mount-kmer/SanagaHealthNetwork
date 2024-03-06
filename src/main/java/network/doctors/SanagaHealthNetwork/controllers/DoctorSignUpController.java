package network.doctors.SanagaHealthNetwork.controllers;


import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.Hospital;
import network.doctors.SanagaHealthNetwork.model.DoctorSignUpForm;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.repositories.HospitalRepository;
import network.doctors.SanagaHealthNetwork.repositories.RoleRepository;
import network.doctors.SanagaHealthNetwork.service.CreateDoctorService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class DoctorSignUpController {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final DoctorRepository doctorRepository;
    private final RoleRepository roleRepository;
    private final HospitalRepository hospitalRepository;

    public DoctorSignUpController(DoctorRepository doctorRepository, RoleRepository roleRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @ModelAttribute("doctor")
    public DoctorList getDefaultDoctor() {
        return new DoctorList();
    }

    @GetMapping("/provider_join")
    public String providerJoinUs(Model model) {
        DoctorSignUpForm doctorSignUpForm = new DoctorSignUpForm();
        model.addAttribute("doctorForm", doctorSignUpForm);
        return "doctorSignup";
    }

    @ModelAttribute("hospitalList")
    public Iterable<Hospital> hospitalList(){
        return hospitalRepository.findAll();
    }

    @ModelAttribute("practiceList")
    public List<String> practiceList(){
        return Arrays.asList("Pediatrician","Orthopedic","Cardiologist","Dentist","Gynaecologist","Psychiatrist", "Nutritionist");
    }


    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @PostMapping("/provider_signup")
    public  String signUpProviderNow(@Valid @ModelAttribute("doctorForm") DoctorSignUpForm doctorSignUpForm, BindingResult result, Model model) {

        DoctorList doctorList = doctorRepository.searchByEmail(doctorSignUpForm.getEmail());
        if (doctorList != null){
            model.addAttribute("emailError", "Email already exist!");
            return "doctorSignup";
        }

        if (result.hasErrors()){
            model.addAttribute("validationError",result.getAllErrors());
            return "doctorSignup";

        } else {
            CreateDoctorService createDoctorService = new CreateDoctorService(roleRepository, doctorRepository);
            createDoctorService.createNewDoctor(doctorSignUpForm.getFirstName(), doctorSignUpForm.getLastName(),
                    doctorSignUpForm.getPractice(), doctorSignUpForm.getHospitalId(), doctorSignUpForm.getPassword(),
                    doctorSignUpForm.getEmail(), doctorSignUpForm.getPhoneNumber(),doctorSignUpForm.getGender(),
                    doctorSignUpForm.getDateOfBirth());
            return "doctorSignupSuccess";
        }

    }
}
