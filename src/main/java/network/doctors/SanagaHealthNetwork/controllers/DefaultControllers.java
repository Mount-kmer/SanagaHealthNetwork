package network.doctors.SanagaHealthNetwork.controllers;


import network.doctors.SanagaHealthNetwork.entity.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;


@ControllerAdvice
public class DefaultControllers {

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }
//
    @ModelAttribute("genderItems")
    public List<String> getGender() {
        return Arrays.asList("Male", "Female", "Other");
    }



}
