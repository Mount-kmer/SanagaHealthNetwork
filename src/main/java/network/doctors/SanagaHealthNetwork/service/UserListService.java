package network.doctors.SanagaHealthNetwork.service;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserListService {
    User getUserById(int userId);
}
