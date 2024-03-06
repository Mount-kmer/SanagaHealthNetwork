package network.doctors.SanagaHealthNetwork.service;

import network.doctors.SanagaHealthNetwork.entity.Appointment;
import network.doctors.SanagaHealthNetwork.entity.Availability;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {
    void createAppointment(int doctorId, int userId, String time, LocalDate date);
    void deleteAppointmentById(int appointmentId);
    List<Appointment> getAppointmentById(int appointmentId);


    boolean isValidAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();
    List<Appointment> getAppointmentByDoctorId(int doctorId);
    List<Appointment> getAppointmentByUserId(int userId);
    List<Appointment> getAppointmentsForDoctorAtDate(int doctorId, LocalDate day);
    String formatDate(LocalDate date) throws ParseException;
    LocalTime formatTime(String localTime);


    //availability methods

    void createDoctorAvailability(LocalDate date, LocalTime startTime, LocalTime endTime);
    Availability getAvailabilityByDoctorId(int doctorId);


}
