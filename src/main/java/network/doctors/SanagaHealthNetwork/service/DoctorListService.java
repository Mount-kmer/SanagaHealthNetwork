package network.doctors.SanagaHealthNetwork.service;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public interface DoctorListService {

    DoctorList getDoctorById(int doctorId);
    Optional<DoctorList> getDocApp(int id);
    List<DoctorList> getAllDoctors();
    List<DoctorList> getAllDoctorsAppointments();
    void seDoctorAvailability(LocalDate localDate, LocalTime startTime, LocalTime endTime);
    LocalDateTime getDoctorAvailability();
}
