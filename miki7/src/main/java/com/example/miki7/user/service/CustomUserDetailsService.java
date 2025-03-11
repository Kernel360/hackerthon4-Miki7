package com.example.miki7.user.service;

import com.example.miki7.user.db.UserEntity;
import com.example.miki7.user.db.UserRepository;
import com.example.miki7.user.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = (UserEntity) userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));

        return new CustomUserDetails(user);
    }

}
