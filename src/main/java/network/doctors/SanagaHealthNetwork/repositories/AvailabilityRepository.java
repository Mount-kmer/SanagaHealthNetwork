package network.doctors.SanagaHealthNetwork.repositories;

import network.doctors.SanagaHealthNetwork.entity.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability,Integer> {
}
