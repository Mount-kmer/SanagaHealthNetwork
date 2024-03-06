package network.doctors.SanagaHealthNetwork.service;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import network.doctors.SanagaHealthNetwork.entity.Hospital;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GoogleCalendarService {

        private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
        private final Logger logger = Logger.getLogger((GoogleCalendarService.class.getName()));

        @Value("classpath:static/json/client_secret_2_476488849566-artu7pq5snfq4l6ahtdsrt0l1sjnntl6.apps.googleusercontent.com.json")
        private Resource credentialResource;

        private Credential credential() throws GeneralSecurityException, IOException {
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(credentialResource.getInputStream()));
            logger.log(Level.INFO, "SECRETS: " + clientSecrets.getDetails().getClientSecret() + " " + clientSecrets.getDetails().getTokenUri() +" " + clientSecrets.getDetails().getRedirectUris());

            GoogleAuthorizationCodeFlow codeFlow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets,
                    Collections.singletonList(CalendarScopes.CALENDAR))
                    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("src/main/resources/tokens")))
                    .setAccessType("offline")
                    .build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            Credential credential = new AuthorizationCodeInstalledApp(codeFlow,receiver).authorize("user");

            if (credential.getExpiresInSeconds() != null && credential.getExpiresInSeconds() <= 0) {
                boolean refreshTokens = credential.refreshToken();

                if (!refreshTokens) {
                    logger.log(Level.SEVERE, "Failed to refresh tokens");
                }

            }

            return credential;
        }

        private Calendar getCalendar() throws IOException, GeneralSecurityException {


            return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(),JSON_FACTORY,credential())
                    .setApplicationName("SanagaHealthApp")
                    .build();
        }

        public List<Event> getEvents() throws IOException, GeneralSecurityException {

            try {

                DateTime now = new DateTime(System.currentTimeMillis());
                Events events = getCalendar().events().list("primary")
                        .setMaxResults(10)
                        .setTimeMin(now)
                        .setTimeZone("America/Chicago")
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();
                logger.log(Level.INFO, "Events", events.getSummary() + events.getDescription());
                return events.getItems();

            } catch (GoogleJsonResponseException exception) {
                logger.log(Level.SEVERE, "Google APi error: " + exception.getDetails().getMessage());
                throw exception;

            }catch (IOException e) {
                logger.log(Level.SEVERE,"IOEXCEPTION: " + e.getMessage());
                throw e;
            }

        }

        public List<TimePeriod> getTimeSlots(String calendarId, DateTime startTime, DateTime endTime) throws GeneralSecurityException, IOException {
                Calendar service = getCalendar();

                FreeBusyRequestItem requestItem = new FreeBusyRequestItem().setId(calendarId);
                FreeBusyRequest request = new FreeBusyRequest()
                        .setTimeMin(startTime)
                        .setTimeMax(endTime)
                        .setTimeZone("America/Chicago")
                        .setItems(Collections.singletonList(requestItem));
                FreeBusyResponse response = service.freebusy().query(request).execute();
                FreeBusyCalendar calendar = response.getCalendars().get(calendarId);

                List<TimePeriod> busy = calendar.getBusy();
                Boolean empty = calendar.isEmpty();

        //        logger.log(Level.INFO, "Busy times " + busy);
        //        logger.log(Level.INFO, "Empty?:" + empty);
                return busy;
        }




        public List<LocalTime> calculateFreeSlots(List<TimePeriod> busySlots, DateTime queryStart, DateTime queryEnd, Duration slotDuration) {
            List<LocalTime> freeSlots = new ArrayList<>();

            // Convert queryStart and queryEnd to ZonedDateTime
            ZonedDateTime start = ZonedDateTime.ofInstant(Instant.ofEpochMilli(queryStart.getValue()), ZoneId.systemDefault());
            ZonedDateTime end = ZonedDateTime.ofInstant(Instant.ofEpochMilli(queryEnd.getValue()), ZoneId.systemDefault());

            // Sort busySlots by start time
            busySlots.sort(Comparator.comparing(o -> o.getStart().getValue()));

            // Define working hours (8 AM to 5 PM) within the query range
            ZonedDateTime workingHoursStart = start.withHour(8).withMinute(0).withSecond(0).withNano(0);
            ZonedDateTime workingHoursEnd = end.withHour(17).withMinute(0).withSecond(0).withNano(0);
    //        ZonedDateTime endtime = end.withHour().withMinute(0).withSecond(0).withNano(0);

            ZonedDateTime current = workingHoursStart;
            for (TimePeriod busySlot : busySlots) {
                ZonedDateTime busyStart = ZonedDateTime.ofInstant(Instant.ofEpochMilli(busySlot.getStart().getValue()), ZoneId.systemDefault());
                ZonedDateTime busyEnd = ZonedDateTime.ofInstant(Instant.ofEpochMilli(busySlot.getEnd().getValue()), ZoneId.systemDefault());

                // Add free slots until the busy slot starts
                while (current.isBefore(busyStart) && !current.plus(slotDuration).isAfter(busyStart)) {
                    freeSlots.add(current.toLocalTime());
                    current = current.plus(slotDuration);
                }

                // Move current time to the end of the busy slot
                current = busyEnd;
            }

            // After processing all busy slots, add any remaining free slots up to the end of working hours
            while (current.isBefore(workingHoursEnd) && !current.plus(slotDuration).isAfter(workingHoursEnd)) {
                freeSlots.add(current.toLocalTime());
                current = current.plus(slotDuration);
            }
            logger.log(Level.INFO,"FREE SLOTS::" + freeSlots);
            return freeSlots;
        }


        //create calendar events
        public void createCalendarEvent( Hospital hospital, String calendarId,
                                        String startTime, String endTime, String patientName) throws IOException, GeneralSecurityException {

            Credential credential = credential();

            Calendar service = new Calendar.Builder(new NetHttpTransport(), new GsonFactory(), credential)
                    .setApplicationName("SanagaHealthApp")
                    .build();

            Event event = new Event()
                    .setSummary("Consultation Appointment at" + hospital.getHospitalName())
                    .setLocation(hospital.getHospitalAddresses().getStreet() + ", "
                            + hospital.getHospitalAddresses().getCity() + ", "
                         + hospital.getHospitalAddresses().getCountry())
                    .setDescription("Consultation appointment with  " + patientName);

            DateTime startDateTime = new DateTime(startTime);
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("America/Chicago");
            event.setStart(start);

            DateTime endDateTime = new DateTime(endTime);
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("America/Chicago");
            event.setEnd(end);

            event = service.events().insert(calendarId, event).execute();
            logger.log(Level.INFO,"Event created: %s\n", event.getHtmlLink());

        }





}

