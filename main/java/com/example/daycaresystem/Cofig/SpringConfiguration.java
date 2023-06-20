package com.example.daycaresystem.Cofig;


import com.example.daycaresystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

    private final MyUserDetailsService myUserDetailsService;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/daycares/add-daycare").permitAll()
                .requestMatchers("/api/v1/daycares/AllDayCares").permitAll()
                .requestMatchers("/api/v1/parent/addParent").permitAll()
                .requestMatchers("/api/v1/parent /parents/{parentId}/bookings").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/parent/DeletePercent/{parent_id}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/parent/addBook/{id}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/Booking/addParent").permitAll()
                .requestMatchers("/api/v1/child/deleteBooking/{id}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/child/get").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/child/update/{id}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/child/delete/{id}").hasAnyAuthority("parent")
//                .requestMatchers("/api/v1/Booking/makeRating/{parentid}/{bookId}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/Booking/updateBooking/{id}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/Booking/deleteBooking/{id}").hasAnyAuthority("parent")
//                .requestMatchers("/api/v1/parent/child/{parentid}").hasAnyAuthority("parent")
//                .requestMatchers("/api/v1/parent/getChildBy/{email}").hasAnyAuthority("parent")
                .requestMatchers("/api/v1/daycares/update-daycare/{id}").hasAnyAuthority("daycare")
                .requestMatchers("/api/v1/daycares/delete-Daycare").hasAuthority("daycare")
                .requestMatchers("/api/v1/daycares/{daycareId}/staff").hasAuthority("daycare")
                .requestMatchers("/api/v1/daycares/{daycareId}/Package").hasAuthority("daycare")
                .requestMatchers("/api/v1/daycares/{name}/hours").permitAll()
                .requestMatchers("/api/v1/daycares/findDayCareByName/{name}").permitAll()
                .requestMatchers("/api/v1/daycares/search/{facilities}").permitAll()
                .requestMatchers("/api/v1/daycares/highest-rate").permitAll()
                .requestMatchers("/api/v1/daycares/changeStatues/{parentId}/{bookId}").hasAuthority("daycare")
                .requestMatchers("/api/v1/auth/login").hasAnyAuthority("daycare" , "parent")
                .anyRequest().permitAll() // by dufulte بيكون مقفل // يعني مفتوح للكل
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID") //
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
