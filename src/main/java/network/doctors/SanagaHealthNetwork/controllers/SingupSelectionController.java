package network.doctors.SanagaHealthNetwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingupSelectionController {

    @GetMapping("/user-selection")
    public String userSelection(){
        return "userSelection";

    }
}
