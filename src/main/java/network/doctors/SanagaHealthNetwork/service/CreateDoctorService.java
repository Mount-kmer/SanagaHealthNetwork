package network.doctors.SanagaHealthNetwork.service;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.Hospital;
import network.doctors.SanagaHealthNetwork.entity.Roles;
import network.doctors.SanagaHealthNetwork.model.Gender;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.repositories.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CreateDoctorService {

    private final RoleRepository roleRepository;

    private final DoctorRepository doctorRepository;

    public CreateDoctorService(RoleRepository roleRepository, DoctorRepository doctorRepository) {
        this.roleRepository = roleRepository;
        this.doctorRepository = doctorRepository;
    }

    public void createNewDoctor(String firstname, String lastname, String practice
            , int hospitalId, String password, String email, String phoneNumber, Gender gender
            , Date dob){

        DoctorList doctorList = new DoctorList();
        doctorList.setFirstName(firstname);
        doctorList.setLastName(lastname);
        doctorList.setAreaOfPractice(practice);
        Hospital hospital = new Hospital();
        hospital.setId(hospitalId);
        hospital.setId(doctorList.setHospitalWorked(hospital));
        doctorList.setHospitalWorked(hospital);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        doctorList.setPassword(passwordEncoder.encode(password));
        doctorList.setEmail(email);
        doctorList.setPhoneNumber(phoneNumber);
        doctorList.setGender(gender);
        doctorList.setDateOfBirth(dob);

        String userDefaultRole = "DOCTOR";

        Roles defaultRole = roleRepository.findByName(userDefaultRole)
                .orElseThrow(() -> new RuntimeException("cannot find role with name" + userDefaultRole));
        Set<Roles> roles = new HashSet<>();
        roles.add(defaultRole);
        doctorList.setRoles(roles);
        doctorList.setAccount_non_locked(true);
        doctorList.setAccount_non_expired(true);
        doctorList.setEnabled(true);
        doctorList.setCredentials_non_expired(true);
        doctorList.setFailedAuthenticationAttempt(0);
        doctorRepository.save(doctorList);
    }
}
