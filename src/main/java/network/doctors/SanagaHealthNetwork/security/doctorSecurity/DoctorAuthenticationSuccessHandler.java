package network.doctors.SanagaHealthNetwork.security.doctorSecurity;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.security.securityservice.DoctorLoginAttemptInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DoctorAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final DoctorLoginAttemptInterceptor loginAttemptInterceptor;

    private  final DoctorRepository doctorRepository;

    public DoctorAuthenticationSuccessHandler(DoctorLoginAttemptInterceptor loginAttemptInterceptor, DoctorRepository doctorRepository) {
        this.loginAttemptInterceptor = loginAttemptInterceptor;
        this.doctorRepository = doctorRepository;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String email = request.getParameter("username");
        logger.info("param received " + email);
        DoctorList doctor = doctorRepository.searchByEmail(email);
        logger.info("doctor "+ doctor);
        if (doctor !=  null){
            logger.info("Initiating successful login of user " + email);
            if (doctor.getFailedAuthAttempts() > 0) {
                loginAttemptInterceptor.resetFailedAuthenticationAttempts(doctor.getEmail());
                logger.info("Successfully logged in user " + email);
            }
            super.setDefaultTargetUrl("/providerProfile");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
