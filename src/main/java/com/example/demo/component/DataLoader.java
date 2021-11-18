package com.example.demo.component;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    private String initMode;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            saveUser("admin", "Developer");
        }
    }

    public void saveUser(String username, String name){
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);
    }
}
