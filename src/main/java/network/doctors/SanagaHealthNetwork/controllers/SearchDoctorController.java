package network.doctors.SanagaHealthNetwork.controllers;


import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchDoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/search")
        public String  search(Model model) {
        List<DoctorList> doctorsList = new ArrayList<>();
        doctorsList = (List<DoctorList>) doctorRepository.findAll();
        model.addAttribute("doctorsList", doctorsList);
            return "search";
    }

//    @GetMapping("{id}")
//    public Optional<DoctorList> findDoc(@PathVariable Integer id ) {
//
//        return doctorRepository.findById(id);
//    }
}
