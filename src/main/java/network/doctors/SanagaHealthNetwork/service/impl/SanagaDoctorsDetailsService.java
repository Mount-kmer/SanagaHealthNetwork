package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service("doctorDetailsService")
public class SanagaDoctorsDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;
    private static final Logger logger = LoggerFactory.getLogger(SanagaDoctorsDetailsService.class);

    public SanagaDoctorsDetailsService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        logger.info("received email param{}" +  email);
        DoctorList doctor =  doctorRepository.searchByEmail(email);

        if (doctor == null){
            logger.info("Username is null");
            throw  new UsernameNotFoundException("User not found with email" + email);
        }

        if (!doctor.getAccountNonLocked()){
            throw new RuntimeException("blocked");
        }

        List<GrantedAuthority> grantedAuthorities = doctor.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(doctor.getEmail(), doctor.getPassword(),
                grantedAuthorities);
    }

    public Iterable<?> getALlDoctors(){
        return doctorRepository.findAll();
    }
}
