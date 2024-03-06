//package network.doctors.SanagaHealthNetwork.security;
//
//
//import network.doctors.SanagaHealthNetwork.service.LoginAttemptService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component
//public class AuthenticationFailureListener  implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
//
//    @Autowired
//    private HttpServletRequest request;
//
//    @Autowired
//    private LoginAttemptService loginAttemptService;
//
//    @Autowired
//    private LoginAttemptFromIP loginAttemptFromIP;
//
//
//    @Override
//    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
//        final String xfHeader = request.getHeader("X-Forwarded-For");
//
//        if (xfHeader == null || xfHeader.isEmpty() || xfHeader.contains(request.getRemoteAddr())) {
//            loginAttemptFromIP.failedLogin(request.getRemoteAddr());
//        } else {
//            loginAttemptFromIP.failedLogin(xfHeader.split(",")[0]);
//        }
//    }
//}
