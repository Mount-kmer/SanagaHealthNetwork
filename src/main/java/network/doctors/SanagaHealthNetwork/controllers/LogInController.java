package network.doctors.SanagaHealthNetwork.controllers;

//import doctornetwork.sanagahealth.beans.Login;
//import doctornetwork.sanagahealth.beans.User;
//import doctornetwork.sanagahealth.exceptions.ApplicationException;
//import doctornetwork.sanagahealth.repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import javax.servlet.http.HttpSession;
//
//
//@Controller
//@SessionAttributes("login")
//public class LogInController {
//
//    private final UserRepository userRepository;
//
//    public LogInController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @ModelAttribute("login")
//    public Login getDefaultLogin() {
//        return new Login();
//    }
//
//    @RequestMapping("/login")
//    public String login(@ModelAttribute("login")Login login, HttpSession session){
//        System.out.println("in login controller");
//        User user  = userRepository.searchByName(login.getUsername());
//        if(user==null){
//            throw new ApplicationException("User not found");
//        }
//        if(user.getUserName().equals(login.getUsername()) && user.getUserPassword().equals(login.getPassword())){
//           return  "forward:/userprofile";
//        }
//        return "index";
//
//    }
//
//
//   @ExceptionHandler(ApplicationException.class)
//       public String keepExceptions(){
//           System.out.println("error occurred in login controller now handling the exception");
//           return "error";
//       }
//   }



