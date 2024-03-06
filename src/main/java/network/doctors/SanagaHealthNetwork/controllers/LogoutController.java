package network.doctors.SanagaHealthNetwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/user-logout")
    public ModelAndView logout(HttpSession session){
        System.out.println("Ending user session");
        session.invalidate();
        //System.out.println(session.getAttribute("login"));
        return new ModelAndView("redirect:/user-home");

    }

//    @GetMapping("/user-logout")
//    public String logout(HttpSession httpSession){
//        httpSession.getAttributeNames();
//        httpSession.getId();
//        httpSession.invalidate();
//        return "index";
//    }
}
