package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.DoctorList;
import network.doctors.SanagaHealthNetwork.entity.Roles;
import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.repositories.RoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SanagaDoctorsDetails implements UserDetails {

    private final DoctorList doctorList;
    private final DoctorRepository doctorRepository;

    private final RoleRepository roleRepository;

    public SanagaDoctorsDetails(DoctorList doctorList, DoctorRepository doctorRepository, RoleRepository roleRepository) {
        this.doctorList = doctorList;
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Roles> roles = doctorList.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return doctorList.getPassword();
    }

    @Override
    public String getUsername() {
        return doctorList.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return doctorList.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return doctorList.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return doctorList.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return doctorList.getAccountIsEnabled();
    }

}
