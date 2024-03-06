package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.Availability;
import network.doctors.SanagaHealthNetwork.service.AvailabilityService;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AvailabilityImp implements AvailabilityService {
    @Override
    public void createAvailability(DateTimeFormat timeFormat, Date date, int doctorId) {

    }

    @Override
    public void edithAvailability(Availability availability) {

    }

    @Override
    public void deleteAvailability() {

    }
}
