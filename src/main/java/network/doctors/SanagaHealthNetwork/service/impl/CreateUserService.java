package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.Roles;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.model.Gender;
import network.doctors.SanagaHealthNetwork.repositories.RoleRepository;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CreateUserService  {

    private final RoleRepository roleRepository;

    private  final UserRepository userRepository;


    public CreateUserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    public void createNewUser(String firstname, String lastname, String email,
                              String password, Date dateOfBirth, Gender gender){
        User newUser = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        newUser.setFirstName(firstname);
        newUser.setLastName(lastname);
        newUser.setEmail(email);
        newUser.setUserPassword(bCryptPasswordEncoder.encode(password));
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setEnabled(true);
        newUser.setAccount_non_locked(true);
        newUser.setAccount_non_expired(true);
        newUser.setGender(gender);
        String userDefaultRole = "USER";

        Roles defaultRole = roleRepository.findByName(userDefaultRole)
                .orElseThrow(() -> new RuntimeException("cannot find role with name" + userDefaultRole));
        Set<Roles> roles = new HashSet<>();
        roles.add(defaultRole);
        newUser.setRoles(roles);

        userRepository.save(newUser);
    }
}
