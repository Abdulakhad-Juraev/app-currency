package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    public UserDetails getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsernameEqualsIgnoreCase(s).orElseThrow(() -> new ResourceNotFoundException("getUsername"));
    }

    //Get_One_User
    public UserDto getUserOne(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUsername());
    }
}
