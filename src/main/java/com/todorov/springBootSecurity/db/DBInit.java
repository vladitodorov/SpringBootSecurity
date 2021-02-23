package com.todorov.springBootSecurity.db;

import com.todorov.springBootSecurity.model.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {
    private final UserRepository userRepository;

    public DBInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Create users into DB
        UserEntity admin = new UserEntity("admin", "Admin123", "ADMIN", "ACCESS_TEST1, ACCESS_TEST2");
        UserEntity user = new UserEntity("user", "User123", "USER", "");
        UserEntity manager = new UserEntity("manager", "Manager123", "MANAGER", "ACCESS_TEST1");

        List<UserEntity> users = Arrays.asList(admin,user,manager);

        //Save to DB
        this.userRepository.saveAll(users);

    }
}
