package doctornetwork.sanagahealth;


import network.doctors.SanagaHealthNetwork.config.ApplicationConfig;
import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ApplicationConfig.class)
//@DataJpaTest

//@ExtendWith(SpringExtension.class)

class SanagaHealthApplicationTests {


	@Autowired
	private UserRepository userRepository;


	@Test
	public void testFindByLastName() {
		// Now you can use the userRepository in your test method
		// For example:
		String lastName = "Smith";

//		List<User> user = userRepository.findByLastName(lastName);
//		System.out.println(user);

		// Perform assertions or other test logic with the retrieved user
		// ...
	}


	public static void main(String[] args) {
		final AppointmentService appointmentService;
		DoctorList doctorList = new DoctorList();
//		System.out.println(doctorList.getFirstName());
		LocalTime currentTime = LocalTime.now();

		// Format the LocalTime as a string with AM/PM marker
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		String formattedTime = currentTime.format(formatter);

	}


}




