package network.doctors.SanagaHealthNetwork.repositories;

import network.doctors.SanagaHealthNetwork.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,Integer > {

//    @Query("select u from Appointment u where u.localDate = :localDate")
//    Appointment findByDate(@Param("date") LocalDate date);

//    List<Appointment> findBy(Date date);

//    @Query("select u from Appointment u where u.time = :time and u.id = :id")
//    List<String> getBookedTimeSlots(@Param("name") int id);


    @Query("select a from Appointment a where a.doctorList.id = :doctorId")
    List<Appointment> findByDoctorId(@Param("doctorId") int doctorId);

    @Query("select a from Appointment a where a.user.id = :userId")
    List<Appointment> findByUserId(@Param("userId") int doctorId);



}