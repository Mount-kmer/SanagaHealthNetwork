package network.doctors.SanagaHealthNetwork.rest;

import network.doctors.SanagaHealthNetwork.entity.Appointment;
import network.doctors.SanagaHealthNetwork.repositories.AppointmentRepository;
import network.doctors.SanagaHealthNetwork.restDTO.AppointmentDTO;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments/")
public class AppointmentRestController {
    private final AppointmentRepository appointmentRepository;

    public AppointmentRestController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping()
    @ResponseBody
    List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointments::add);

        return appointments;
    }

    @GetMapping("/{userId}")
    public List<AppointmentDTO> getAppointmentById(@PathVariable("userId")Integer userId) throws ParseException {
        List<Appointment> appointment =  appointmentRepository.findByUserId(userId);
        return appointment.stream()
                .map(this::presentAsDTO)
                .collect(Collectors.toList());
    }

    private AppointmentDTO presentAsDTO(Appointment appointment)   {
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        try {
            appointmentDTO.setDoctorFN(appointment.getDoctorList().getFirstName());
            appointmentDTO.setDoctorLN(appointment.getDoctorList().getLastName());
            appointmentDTO.setTime(appointment.getTime());
            appointmentDTO.setDate(appointment.getLocalDate());
            appointmentDTO.setHospital(appointment.getDoctorList().getHospitalWorked().getHospitalName());
            appointmentDTO.setAddress(appointment.getDoctorList().
                    getHospitalWorked().getHospitalAddresses().getStreet() + " " +
                    " " + appointment.getDoctorList().getHospitalWorked().getHospitalAddresses().getCity() + " "
                    + " " + appointment.getDoctorList().getHospitalWorked().getHospitalAddresses().getCountry());
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        return appointmentDTO;

    }
}
