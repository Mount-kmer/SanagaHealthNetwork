package network.doctors.SanagaHealthNetwork.repositories;


import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorList, Integer> {
    List<DoctorList> findByLastName(String name);

    @Query("select u from DoctorList u where u.email =:email")
    DoctorList searchByEmail(@Param("email") String email);

    @Query("select u from DoctorList u where u.lastName =:lastname")
    DoctorList searchByName(@Param("lastname") String lastname);


    @Query("select u from DoctorList u where u.id =:id")
    DoctorList searchById(@Param("id") int id);
//    @Query("select p from Product p where p.name like %:name%")
//    public List<Product> searchByName(@Param("name") String name);
//    @Query("select d from DoctorList d where d.address like %:address")
//    List<DoctorList> fingBy(@RequestParam("city") String address);
    
//    List<DoctorList> findByDoctorId(Integer id);

    @Modifying
    @Query("update DoctorList u set u.failedAuthenticationAttempt = :failedAttempts where u.email = :email")
    void updateFailedAuthenticationAttempts(@Param("failedAttempts") int failedAttempts, @Param("email") String email);




}
