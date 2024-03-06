package doctornetwork.sanagahealth;


import network.doctors.SanagaHealthNetwork.config.ApplicationConfig;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.AppointmentService;
import network.doctors.SanagaHealthNetwork.service.LoginAttemptService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.logging.Logger;


@ContextConfiguration(classes = ApplicationConfig.class)
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
//@DataJpaTest
//@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
public class UserRepositoryTest {
//        @Autowired


    private final UserRepository userRepository;

    private final DoctorRepository doctorRepository;


    private final AppointmentService appointmentService;

//    private final SanagaUserDetailsService service;

    private static final Logger LOGGER = Logger.getLogger("InfoLogging");
//

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, DoctorRepository doctorRepository, AppointmentService appointmentService) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentService = appointmentService;
    }


//    @Test
//    public void testUser() {
//        User user = new User("s", "p");
//        user.setFirstName("daniel");
//        user.setLastName("kill");
//        user.setUserName("testuser");
//        user.setEmail("dan2@yahoo.com");
//        user.setGender(Gender.MALE);
//        user.setDateOfBirth(new Date(6/12/1993));
//        user.setUserPassword("password123");
//        User saveduser = userRepository.save(user);
////        User existingUser = entityManager.find(User.class, saveduser.getId());
//        assert user.getUserName().equals(saveduser.getUserName());
//
//    }


//    @Test
//    public void testCreateAppointment() {
//        Appointment appointment = new Appointment();
//        AppointmentForm appointmentForm = new AppointmentForm();
//
//        appointmentService.createAppointment(12,"8:00", LocalDate.ofEpochDay(2022-07-01));
//
//
//    }
//
    @Test
    public void retrieveUser() {
//        String username = "cedric.gaston@gmail.com";
////        SanagaUserDetails userDetails = (SanagaUserDetails) service.loadUserByUsername(username);
//        DoctorList doctor = doctorRepository.searchByEmail(username);
//        System.out.println("Fetching user with email: " + username);
//        System.out.println("User fetched: " + doctor);

        LoginAttemptService loginAttemptService = new LoginAttemptService(userRepository);
        User user =  userRepository.searchByEmail("daniel.mbouzou@gmail.com");
        userRepository.updateFailedAuthenticationAttempts(2, user.getEmail());
        userRepository.save(user);
//        loginAttemptService.resetFailedAuthenticationAttempts(user.getEmail());
        System.out.println(user.getFailedAuthenticationAttempt());
        System.out.println(user.getAccount_non_locked());


//        LOGGER.log(Level.INFO, String.valueOf(userDetails.isEnabled()));
//        LOGGER.log(Level.INFO, String.valueOf(userDetails.getPassword()));
//        LOGGER.log(Level.INFO, String.valueOf(userDetails.getFirstName()));
//        LOGGER.log(Level.INFO, String.valueOf(userDetails.getLastName()));
//        LOGGER.log(Level.INFO, String.valueOf(userDetails.getAuthorities()));
//
//        assertEquals(username, userDetails.getUsername());

//        verify(userRepository, times(1)).searchByName(username);

    }
}