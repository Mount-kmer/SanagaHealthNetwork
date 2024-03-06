package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.service.DoctorListService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorListServiceImpl implements DoctorListService {
    private final DoctorRepository doctorRepository;

    public DoctorListServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorList getDoctorById(int doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UsernameNotFoundException("Doctor not found!"));
    }

    @Override
    public Optional<DoctorList> getDocApp(int id) {
        return doctorRepository.findById(id);
    }


    @Override
    public List<DoctorList> getAllDoctors() {
        return null;
    }

    @Override
    public List<DoctorList> getAllDoctorsAppointments() {
        return null;
    }

    @Override
    public void seDoctorAvailability(LocalDate localDate, LocalTime startTime, LocalTime endTime) {

    }



    @Override
    public LocalDateTime getDoctorAvailability() {
        return null;
    }
}
