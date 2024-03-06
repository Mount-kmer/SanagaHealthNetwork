package network.doctors.SanagaHealthNetwork.controllers;


//import jakarta.validation.Valid;

import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.model.Login;
import network.doctors.SanagaHealthNetwork.model.UserSignUpForm;
import network.doctors.SanagaHealthNetwork.repositories.RoleRepository;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.impl.CreateUserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class UserSignUpController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserSignUpController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "dateOfBirth",
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute("signup") UserSignUpForm signUpForm, BindingResult result, Model model,
                         @ModelAttribute("login") Login login) {
        User existingUser = userRepository.searchByEmail(signUpForm.getEmail());
            if (existingUser != null) {
                model.addAttribute("message", "Email already exists.");
                return "/signup";
            }

            if (result.hasErrors()){
                model.addAttribute("validationError", result.getAllErrors());
                return "/signup";
            } else {

                CreateUserService createUserService = new CreateUserService(roleRepository, userRepository);
                createUserService.createNewUser(signUpForm.getFirstName(), signUpForm.getLastName(),
                        signUpForm.getEmail(),signUpForm.getPassword(), signUpForm.getDateOfBirth(),
                        signUpForm.getGender());
                model.addAttribute("dataSaved", "user was registered");
                return "registration_success";
            }
    }

}