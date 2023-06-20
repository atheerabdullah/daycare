package com.example.daycaresystem.Service;


import com.example.daycaresystem.Model.MyUser;
import com.example.daycaresystem.Repository.MyUserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserAuthRepository myUserAuthRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myuser = myUserAuthRepository.findMyUserByUsername(username);

        if (myuser== null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return myuser;
    }
}
