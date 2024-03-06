package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.Appointment;
import network.doctors.SanagaHealthNetwork.entity.Availability;
import network.doctors.SanagaHealthNetwork.model.AppointmentStatus;
import network.doctors.SanagaHealthNetwork.repositories.AppointmentRepository;
import network.doctors.SanagaHealthNetwork.repositories.AvailabilityRepository;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.AppointmentService;
import network.doctors.SanagaHealthNetwork.service.DoctorListService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    private static Logger logger;
    private final AppointmentRepository appointmentRepository;
    private final DoctorListService doctorListService;
    private final AvailabilityRepository availabilityRepository;
    private final SanagaUserDetailsService sanagaUserDetailsService;
    private final UserRepository userRepository;

    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository, DoctorListService doctorListService,
                                            AvailabilityRepository availabilityRepository, SanagaUserDetailsService sanagaUserDetailsService, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorListService = doctorListService;
        this.availabilityRepository = availabilityRepository;
        this.sanagaUserDetailsService = sanagaUserDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    public void createAppointment(int doctorId, int userId, String time, LocalDate date) {
        Appointment appointment = new Appointment();
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointment.setDoctorList(doctorListService.getDoctorById(doctorId));
        appointment.setUserList(userRepository.searchById(userId));
        appointment.setTime(time);
        appointment.setLocalDate(date);
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(int appointmentId) {

    }

    @Override
    public List<Appointment> getAppointmentById(int appointmentId) {
        return null;
    }

    @Override
    public boolean isValidAppointment(Appointment appointment) {

        return false;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentByDoctorId(int doctorId) {
        return (List<Appointment>) appointmentRepository.findByDoctorId(doctorId);

    }

    @Override
    public List<Appointment> getAppointmentByUserId(int userId) {
        return (List<Appointment>) appointmentRepository.findByUserId(userId);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctorAtDate(int doctorId, LocalDate day) {
        return null;
    }






    public String formatDate(LocalDate date) throws ParseException {
        String dateValue = date.toString();

        LocalDate localDate = LocalDate.parse(dateValue);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String weekNumber = new SimpleDateFormat("dd").format(dateFormat.parse(dateValue));
        String dayOfWeek = new SimpleDateFormat("EEEE").format(dateFormat.parse(dateValue));
        String monthName = new SimpleDateFormat("MMMM").format(dateFormat.parse(dateValue));
        String year = new SimpleDateFormat("yyyy").format(dateFormat.parse(dateValue));
        return dayOfWeek + "  " + monthName + "  " + weekNumber + "  " + year;
    }



    @Override
    public LocalTime formatTime(String localTime) {
        String[] timeParts = localTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return LocalTime.of(hours, minutes);
    }

    @Override
    public void createDoctorAvailability(LocalDate date, LocalTime startTime, LocalTime endTime) {
        Availability availability = new Availability();
        availability.setAppointmentDate(date);
        availability.setStartTime(startTime);
        availability.setEndTime(endTime);
        availabilityRepository.save(availability);
    }

    @Override
    public Availability getAvailabilityByDoctorId(int doctorId) {
        return null;
    }

    public Optional<Appointment> getAppointment(int id) {
       return appointmentRepository.findById(id);
    }

}