package network.doctors.SanagaHealthNetwork.service;


import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class LoginAttemptService {
    public static final  int MAX_FAILED_ATTEMPTS = 3;
    public static  final long LOCK_TIME_DURATION = 2 * 60 * 60 * 1000;

    @Autowired
    private HttpServletRequest request;

    private final UserRepository userRepository;

    public LoginAttemptService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void countFailedAuthenticationAttempts(User user){
        int failedAttempts = user.getFailedAuthenticationAttempt() + 1;
        userRepository.updateFailedAuthenticationAttempts(failedAttempts, user.getEmail());
    }

    public void resetFailedAuthenticationAttempts(String email){
        userRepository.updateFailedAuthenticationAttempts(0, email);
    }

    public void lockOutUser(User user) {
        user.setAccount_non_locked(false);
        user.setLockOutTime(new Date());
        userRepository.save(user);
    }

    public boolean unlockUserAccount(User user) throws ParseException {
        long lockTimeMs = user.getLockOutTime().getTime();
        long currentTimeMs = System.currentTimeMillis();
        if (lockTimeMs + LOCK_TIME_DURATION < currentTimeMs) {
            user.setAccount_non_locked(true);
            String lockoutDate = "1970-01-01 00:00:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(lockoutDate);

            user.setLockOutTime(date);
            user.setFailedAuthenticationAttempt(0);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
