package network.doctors.SanagaHealthNetwork.repositories;


import network.doctors.SanagaHealthNetwork.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByLastName(String name);
//    @Query("select u from User u where u.username like %:name%")
    @Query("select u from User u where u.email =:email")
    User searchByName(@Param("email") String email);

    @Query("select u from User u where u.email = :email")
    User searchByEmail(@Param("email") String email);

    @Query("select u from User u where u.id = :id")
    User searchById(@Param("id") int id);


    @Query("select u from User u where u.passwordResetToken = :token")
    User searchByToken(String token);

//    @Query("UPDATE User u SET u.failedAuthenticationAttempt = ?1 where u.email = ?2")
//    @Modifying
//    void updatedFailedAuthenticationAttempts(int failedAttempts, String email);

    @Modifying
//    @Query(value = "UPDATE \"User\" SET \"failedAuthenticationAttempt\" = :failedAttempts WHERE \"email\" = :email", nativeQuery = true)
    @Query("update User u set u.failedAuthenticationAttempt = :failedAttempts where u.email = :email")
    void updateFailedAuthenticationAttempts(@Param("failedAttempts") int failedAttempts, @Param("email") String email);
}
