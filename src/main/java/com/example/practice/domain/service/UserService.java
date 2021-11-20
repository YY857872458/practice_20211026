package com.example.practice.domain.service;

import com.example.practice.domain.entity.User;
import com.example.practice.domain.entity.UserV02;
import com.example.practice.repository.UserRepository;
import com.example.practice.repository.mapper.UserConvertBasic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    public void addUser(User user) { userRepository.addUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    public List<UserV02> getUsersUnder(Long age) {
        final List<User> usersAgeUnder = userRepository.findUserUnder(age);
        return UserConvertBasic.INSTANCE.toUserV02(usersAgeUnder);
    }
}
