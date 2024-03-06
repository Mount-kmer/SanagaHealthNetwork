package network.doctors.SanagaHealthNetwork.security.doctorSecurity;

import network.doctors.SanagaHealthNetwork.repositories.DoctorRepository;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaDoctorsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfigDoctors extends WebSecurityConfigurerAdapter {

    @Qualifier("doctorDetailsService")
    private final SanagaDoctorsDetailsService sanagaDoctorsDetailsService;

    private final DoctorRepository doctorRepository;

    @Autowired
    private DoctorLogoutSuccessHandler doctorLogoutSuccessHandler;

    @Autowired
    private DoctorAuthenticationFailureHandler doctorAuthenticationFailureHandler;

    @Autowired
    private DoctorAuthenticationSuccessHandler doctorAuthenticationSuccessHandler;


    public SecurityConfigDoctors( SanagaDoctorsDetailsService sanagaDoctorsDetailsService, DoctorRepository doctorRepository) {
        this.sanagaDoctorsDetailsService = sanagaDoctorsDetailsService;
        this.doctorRepository = doctorRepository;
    }

    @Bean
    public UserDetailsService detailsService(){
        return new SanagaDoctorsDetailsService(doctorRepository);
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder2() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider2(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(sanagaDoctorsDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder2());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider2());
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatcher(request -> request.getRequestURI().startsWith("/provider"))
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/provider_join", "/providerProfile","/appointment?/**")
                .permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/provider_login_form")
                .defaultSuccessUrl("/providerProfile")
                .failureUrl("/provider_login_form?error")
                .successHandler(doctorAuthenticationSuccessHandler)
                .failureHandler(doctorAuthenticationFailureHandler)
                .permitAll()
                .and()
                .logout((logout) -> logout.logoutSuccessHandler(doctorLogoutSuccessHandler)
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/provider_logout")
                        .deleteCookies("JSESSIONID").permitAll());
    }


}


