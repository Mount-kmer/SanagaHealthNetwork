package network.doctors.SanagaHealthNetwork.service.impl;

import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("userDetailsService")
public class SanagaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SanagaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.searchByName(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found with  user name " + username);
        }

        if (!user.getAccount_non_locked()){
            throw new RuntimeException("blocked");

        }

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getUserPassword(), grantedAuthorities);
    }

    public UserDetails getUserById(int userId) throws UsernameNotFoundException {
        User user = userRepository.searchById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with that id");
        }
        return new SanagaUserDetails(user, userRepository);
    }

    public UserDetails getUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.searchByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with email " + email);
        }
        return new SanagaUserDetails(user, userRepository);
     }
}
