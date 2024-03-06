package network.doctors.SanagaHealthNetwork.controllers;


import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.model.DoctorLogin;
import network.doctors.SanagaHealthNetwork.model.Login;
import network.doctors.SanagaHealthNetwork.model.ResetPassword;
import network.doctors.SanagaHealthNetwork.model.UserSignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/user-home")
    public String home() {
        System.out.println("going to home");
        return "index";
    }

//    @GetMapping("/provider_login")
//    public String getLoginForm() {
//        return "doctorLogin";
//    }

    @RequestMapping(value = "/provider_login_form", method = RequestMethod.GET )
    public String providerLogin(){
        return "doctorLogin";
    }

    @GetMapping("/goToSearch")
    public String searchPage() {
        System.out.println("fetching doctors");
        return "search";
    }

    @GetMapping("/getError")
    public String errorHome(){
        return "error";
    }

    @GetMapping("/goToSignup")
    public String register(Model model) {
        UserSignUpForm userSignUpForm = new UserSignUpForm();
        model.addAttribute("signup", userSignUpForm);
        return "signup";
    }


    @GetMapping("/goToProfile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/register-success")
        public String success() {
            return "registration_success";
    }



    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }


    @ModelAttribute("reset_info")
    public ResetPassword resetPassword() {
        return new ResetPassword();
    }

    @ModelAttribute("resetPassword")
    public ResetPassword getPasswordReset() {
        return new ResetPassword();
    }

    @ModelAttribute("doctor")
    public DoctorList getDefaultDoctor() {
        return new DoctorList();
    }

    @ModelAttribute("login")
    public Login getDefaultLogin() {
        return new Login();
    }

    @ModelAttribute("doctorLogin")
    public DoctorLogin getDefaultDoctorLogin(){
        return new DoctorLogin();
    }

}

