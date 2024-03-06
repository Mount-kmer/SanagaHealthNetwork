package network.doctors.SanagaHealthNetwork.security;

import network.doctors.SanagaHealthNetwork.repositories.UserRepository;
import network.doctors.SanagaHealthNetwork.service.impl.SanagaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public  class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("userDetailsService")
    private final SanagaUserDetailsService sanagaUserDetailsService;

    private final UserRepository userRepository;

    @Autowired
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public CustomLogOutSuccessHandler customLogOutSuccessHandler;

    public SecurityConfig(SanagaUserDetailsService sanagaUserDetailsService, UserRepository userRepository) {
        this.sanagaUserDetailsService = sanagaUserDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    @Primary
    public PasswordEncoder bCryptPasswordEncoder1() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(sanagaUserDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder1());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }
//
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity

//            .requestMatcher(request -> request.getRequestURI().startsWith("/user"))
            .httpBasic()
            .and()
            .authorizeRequests()

            .antMatchers("/forgot-password",
                    "/reset-password","/reset-form","/resources/**","/js/**","/css/**","/images/**","/signup","/error/**",
                    "/register-success","/search","/app","/app/new","/userprofile/search","/appointments/search","/appointment/new?=success",
                    "/src/main/resources/static/js/**","/api/appointments/**", "/appointment/**","/appointments/new/**","/providerProfile",
                    "/doctors","/calendar-events/**","/user-selection", "/goToSignup")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/user-home")
            .defaultSuccessUrl("/userprofile/**", true)
            .failureUrl("/user-home?error")
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .permitAll()

            .and()

            .logout((logout) -> logout.logoutSuccessHandler(customLogOutSuccessHandler)
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/user-logout")
                    .deleteCookies("JSESSIONID").permitAll());
}

//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests().antMatchers("/resources/**","/js/**","/css/**","/images/**").permitAll();
//              httpSecurity
//                .antMatcher("/user/**")
//                .authorizeRequests()
//                .and()
//                    .formLogin()
//                    .loginPage("/user/home")
//                    .defaultSuccessUrl("/user/profile")
//                    .failureUrl("/user/home?error")
//                    .successHandler(customAuthenticationSuccessHandler)
//                    .failureHandler(customAuthenticationFailureHandler)
//                    .permitAll()
//                    .and()
//
//                    .logout((logout) -> logout.logoutSuccessHandler(customLogOutSuccessHandler)
//                            .invalidateHttpSession(true)
//                            .logoutSuccessUrl("/user-logout")
//                            .deleteCookies("JSESSIONID").permitAll());
//
//    }

}


