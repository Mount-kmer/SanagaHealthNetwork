package network.doctors.SanagaHealthNetwork.service;

import network.doctors.SanagaHealthNetwork.entity.Availability;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface AvailabilityService {

    void createAvailability(DateTimeFormat timeFormat, Date date, int doctorId);
    void edithAvailability(Availability availability);
    void deleteAvailability();

}
