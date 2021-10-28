package com.example.practice.repository;

import com.example.practice.domain.entity.User;

import java.util.List;

public interface UserRepository {

    User findUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> findUserUnder(Long age);
}
