package network.doctors.SanagaHealthNetwork.rest;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.HospitalAddress;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.restDTO.DoctorDTO;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaDoctorsDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/appointments/search")
public class DoctorRestController {

    private final DoctorRepository doctorRepository;
    private final SanagaDoctorsDetailsService sanagaDoctorsDetailsService;
    private final Logger logger = Logger.getLogger((DoctorRestController.class.getName()));

    public DoctorRestController(DoctorRepository doctorRepository, SanagaDoctorsDetailsService sanagaDoctorsDetailsService) {
        this.doctorRepository = doctorRepository;
        this.sanagaDoctorsDetailsService = sanagaDoctorsDetailsService;
    }


    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctorDTOS = new ArrayList<>();

        for (DoctorList doctorList : doctorRepository.findAll()) {
            DoctorDTO dto = new DoctorDTO();
            dto.setId(doctorList.getId());
            dto.setFirstname(doctorList.getFirstName());
            dto.setLastname(doctorList.getLastName());
            dto.setPractice(doctorList.getAreaOfPractice());

            if (doctorList.getHospitalWorked() != null) {
                dto.setHospital(doctorList.getHospitalWorked().getHospitalName());

                HospitalAddress address = doctorList.getHospitalWorked().getHospitalAddresses();
                if (address != null) {
                    dto.setAddress(address.getStreet() + ", " + address.getCity() + ", " + address.getCountry());
                }
            }

            dto.setEmail(doctorList.getEmail());

            doctorDTOS.add(dto);
        }

        return ResponseEntity.ok(doctorDTOS);
    }



}
