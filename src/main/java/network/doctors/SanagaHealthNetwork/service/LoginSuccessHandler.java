package network.doctors.SanagaHealthNetwork.service;//package doctornetwork.sanagahealth.service;
//
//import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//        Authentication authentication) throws IOException {
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//
//        String redirectUrl = request.getContextPath();
//        if(customUserDetails.getPassword() == authentication.getCredentials()){
//            redirectUrl = "/userprofile";
//        }
//        response.sendRedirect(redirectUrl);
//    }

//}
