package network.doctors.SanagaHealthNetwork.security;

import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.LoginAttemptService;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginAttemptService loginAttemptService;
    private final UserRepository userRepository;
    private final SanagaUserDetailsService service;

    public CustomAuthenticationSuccessHandler(LoginAttemptService loginAttemptService, UserRepository userRepository, SanagaUserDetailsService service) {
        this.loginAttemptService = loginAttemptService;
        this.userRepository = userRepository;
        this.service = service;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = request.getParameter("username");
        User user = userRepository.searchByEmail(email);
        if(user != null){
            logger.info("USER IS NOT NULL " + user.getEmail());
            if (user.getFailedAuthenticationAttempt() > 0) {
                loginAttemptService.resetFailedAuthenticationAttempts(user.getEmail());
            }
            super.setDefaultTargetUrl("/userprofile");
            super.onAuthenticationSuccess(request, response,authentication);
        }
    }
}
