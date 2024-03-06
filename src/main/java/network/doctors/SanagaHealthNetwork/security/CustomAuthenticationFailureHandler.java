package network.doctors.SanagaHealthNetwork.security;

import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.LoginAttemptService;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaUserDetailsService;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger((CustomAuthenticationFailureHandler.class.getName()));

    private final SanagaUserDetailsService sanagaUserDetailsService;


    private final LoginAttemptService loginAttemptService;
//
//    @Autowired
//    private MessageSource messageSource;

    private final LocaleResolver localeResolver;


    public CustomAuthenticationFailureHandler(UserRepository userRepository, SanagaUserDetailsService sanagaUserDetailsService, LoginAttemptService loginAttemptService, LocaleResolver localeResolver) {
        this.userRepository = userRepository;
        this.sanagaUserDetailsService = sanagaUserDetailsService;
        this.loginAttemptService = loginAttemptService;
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("username");
        logger.log(Level.INFO, "Received request param " + email);
        User user = userRepository.searchByEmail(email);
        if (user!= null) {
            if (exception.getMessage().equalsIgnoreCase("Bad Credentials")) {
                exception = new LockedException("Username or Password Incorrect");
            }

            if (exception.getMessage().equalsIgnoreCase("blocked")) {
                exception = new LockedException("Your account has been locked try again in 24 hours");
            }

            if(user.getIsEnabled() && user.getAccount_non_locked()) {

                if(user.getFailedAuthenticationAttempt() < LoginAttemptService.MAX_FAILED_ATTEMPTS -1) {
                    loginAttemptService.countFailedAuthenticationAttempts(user);
//                    exception = new LockedException("Your account will be locked with another failed attempt");
                    logger.log(Level.INFO, "HERE NOW");
                } else {
                    loginAttemptService.lockOutUser(user);
                    exception = new LockedException("Your account has been locked due to 3 failed attempts");
                    logger.log(Level.INFO, exception.toString());
                }
            } else if(!user.getAccount_non_locked()) {
                try {
                    if (loginAttemptService.unlockUserAccount(user)) {
                        exception = new LockedException("Account has been unlocked");
                        logger.log(Level.INFO, exception.toString());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        super.setDefaultFailureUrl("/user-home?error");
        super.onAuthenticationFailure(request, response, exception);


    }
}



