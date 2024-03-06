package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.Roles;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SanagaUserDetails implements UserDetails {

    private final User user;
    private final UserRepository userRepository;


    public SanagaUserDetails(User user, UserRepository userRepository) {
        this.user = user;
        this.userRepository = userRepository;

    }

//    public SanagaUserDetails(User user, UserRepository userRepository, RoleRepository roleRepository){
//        this.user = user;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Roles> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccount_non_locked();
    }

    @Override
    public boolean isAccountNonLocked() {

        return user.getAccount_non_locked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return
                user.getIsEnabled();
    }

    public String getFirstName() {
        return this.user.getFirstName();
    }

    public String getLastName() {
        return this.user.getLastName();
    }
    public String getMPassword(){
        return this.user.getUserPassword();
    }

    public int getId() {
        return this.user.getId();
    }


}
