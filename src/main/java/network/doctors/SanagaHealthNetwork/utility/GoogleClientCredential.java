//package network.doctors.SanagaHealthNetwork.utility;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.calendar.CalendarScopes;
//import network.doctors.SanagaHealthNetwork.service.GoogleCalendarService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@Component
//public class GoogleClientCredential {
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private final Logger logger = Logger.getLogger((GoogleCalendarService.class.getName()));
//
//    @Value("classpath:static/json/client_secret_2_476488849566-artu7pq5snfq4l6ahtdsrt0l1sjnntl6.apps.googleusercontent.com(2).json")
//    private Resource credentialResource;
//    private Credential  credentialSecrets() throws GeneralSecurityException, IOException {
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(credentialResource.getInputStream()));
//        logger.log(Level.INFO, "SECRETS: " + clientSecrets.getDetails().getClientSecret() + " " + clientSecrets.getDetails().getTokenUri() +" " + clientSecrets.getDetails().getRedirectUris());
//
//        GoogleAuthorizationCodeFlow codeFlow = new GoogleAuthorizationCodeFlow.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets,
//                Collections.singletonList(CalendarScopes.CALENDAR_READONLY))
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("src/main/resources/tokens")))
//                .setAccessType("offline")
//                .build();
//
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        Credential credential = new AuthorizationCodeInstalledApp(codeFlow,receiver).authorize("user");
//
//        if (credential.getExpiresInSeconds() != null && credential.getExpiresInSeconds() <= 0) {
//            boolean refreshTokens = credential.refreshToken();
//
//            if (!refreshTokens) {
//                logger.log(Level.SEVERE, "Failed to refresh tokens");
//            }
//
//        }
//
//        return credential;
//
//    }
//
//    public Credential getCredential() throws GeneralSecurityException, IOException {
//        return credentialSecrets();
//    }
//}
