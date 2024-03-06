package network.doctors.SanagaHealthNetwork.security.securityservice;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class DoctorLoginAttemptInterceptor  {
    public static final  int MAX_FAILED_ATTEMPTS = 3;
    public static  final long LOCK_TIME_DURATION = 2 * 60 * 60 * 1000;

    private final DoctorRepository doctorRepository;


    public DoctorLoginAttemptInterceptor(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void countFailedAuthenticationAttempts(DoctorList doctor) {
        int failedAttempts = doctor.getFailedAuthAttempts() + 1;
        doctorRepository.updateFailedAuthenticationAttempts(failedAttempts, doctor.getEmail());
    }

    public void resetFailedAuthenticationAttempts(String email){
        doctorRepository.updateFailedAuthenticationAttempts(0, email);
    }

    public void lockOutUser(DoctorList doctor) {
        doctor.setAccount_non_locked(false);
        doctor.setLockOutTime(new Date());
        doctorRepository.save(doctor);
    }

    public boolean unlockUserAccount(DoctorList doctor) throws ParseException {
        long lockTimeMilliSec = doctor.getLockOutTime().getTime();
        long currentTimeMs = System.currentTimeMillis();
        if (lockTimeMilliSec + LOCK_TIME_DURATION < currentTimeMs) {
            doctor.setAccount_non_locked(true);
            String lockoutDate = "1970-01-01 00:00:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(lockoutDate);

            doctor.setLockOutTime(date);
            doctor.setFailedAuthenticationAttempt(0);
            doctorRepository.save(doctor);
            return true;
        }
        return false;
    }


}
