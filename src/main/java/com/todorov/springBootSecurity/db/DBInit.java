package com.todorov.springBootSecurity.db;

import com.todorov.springBootSecurity.model.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        //Delete all users from DB
        this.userRepository.deleteAll();

        //Create users into DB
        UserEntity admin = new UserEntity("admin", passwordEncoder.encode( "Admin123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        UserEntity user = new UserEntity("user", passwordEncoder.encode( "User123"), "USER", "");
        UserEntity manager = new UserEntity("manager", passwordEncoder.encode( "Manager123"), "MANAGER", "ACCESS_TEST1");

        List<UserEntity> users = Arrays.asList(admin,user,manager);

        //Save to DB
        this.userRepository.saveAll(users);

    }
}
