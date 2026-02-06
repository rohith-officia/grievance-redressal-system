package com.example.complaint_system.Security;

import com.example.complaint_system.Models.UserModel;
import com.example.complaint_system.Repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with this email " + email)
        );
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
