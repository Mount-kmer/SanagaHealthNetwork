package network.doctors.SanagaHealthNetwork.controllers;

import network.doctors.SanagaHealthNetwork.model.DoctorLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DoctorLoginController {


        @PostMapping("/provider_login_debug")
        public ResponseEntity<?> captureLogin(@RequestParam Map<String, String> params) {
            params.forEach((key, value) -> {
                System.out.println("Key: " + key + ", Value: " + value);
            });
            return ResponseEntity.ok().build();
        }


}
