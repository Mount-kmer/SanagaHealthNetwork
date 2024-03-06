package network.doctors.SanagaHealthNetwork.security.doctorSecurity;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.security.securityservice.DoctorLoginAttemptInterceptor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DoctorAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final DoctorRepository doctorRepository;
    private final DoctorLoginAttemptInterceptor loginAttemptInterceptor;
    private final Logger logger = Logger.getLogger((DoctorAuthenticationFailureHandler.class.getName()));

    public DoctorAuthenticationFailureHandler(DoctorRepository doctorRepository, DoctorLoginAttemptInterceptor loginAttemptInterceptor) {
        this.doctorRepository = doctorRepository;
        this.loginAttemptInterceptor = loginAttemptInterceptor;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String email = request.getParameter("username");
        logger.log(Level.INFO, "Received param "+ email);
        DoctorList doctor = doctorRepository.searchByEmail(email);

        if (doctor != null) {
            if (exception.getMessage().equalsIgnoreCase("Bad Credentials")) {
                exception = new LockedException("Email or password incorrect.");
                logger.log(Level.INFO, exception.toString());
            }

            if (exception.getMessage().equalsIgnoreCase("blocked")){
                exception = new LockedException("Your account has been locked due to too many failed" + " " +
                        "login attempts. Try again in 24 hours");
            }

            if (doctor.getAccountIsEnabled() && doctor.getAccountNonLocked()) {
                if (doctor.getFailedAuthAttempts() < DoctorLoginAttemptInterceptor.MAX_FAILED_ATTEMPTS -1){
                    loginAttemptInterceptor.countFailedAuthenticationAttempts(doctor);
                } else {
                    loginAttemptInterceptor.lockOutUser(doctor);
                    exception = new LockedException("Your account has been locked due to 3 failed attempts");
                    logger.log(Level.INFO, exception.toString());
                }
            } else if (!doctor.getAccountNonLocked()) {
                try {
                    if (loginAttemptInterceptor.unlockUserAccount(doctor)) {
                        exception = new LockedException("Account has been unlocked.");
                        logger.log(Level.INFO , exception.toString());
                    }
                } catch (ParseException e){
                    logger.log(Level.INFO , e.toString());
                }
            }
        }

        super.setDefaultFailureUrl("/provider_login_form?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
