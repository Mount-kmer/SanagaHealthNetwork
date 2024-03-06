package network.doctors.SanagaHealthNetwork.repositories;

import network.doctors.SanagaHealthNetwork.entity.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Integer> {


}
