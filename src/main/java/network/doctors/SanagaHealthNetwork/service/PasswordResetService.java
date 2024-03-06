package network.doctors.SanagaHealthNetwork.service;


import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.exceptions.ApplicationException;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PasswordResetService {

//    @Autowired
    private final UserRepository userRepository;

    public PasswordResetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updatePasswordToken(String token, String email){
        User user = userRepository.searchByEmail(email);
        if (user != null){
            user.setPasswordResetToken(token);
            userRepository.save(user);
        } else {
            throw new ApplicationException("Sorry the following email does not exists " + email);
        }
    }

    public  User getPasswordResetToken(String token) {
        return userRepository.searchByToken(token);
    }

    public void updateUserPassword(User user, String newPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePassword = encoder.encode(newPassword);
        user.setUserPassword(securePassword);
        user.setPasswordResetToken(null);
        userRepository.save(user);
    }
}
