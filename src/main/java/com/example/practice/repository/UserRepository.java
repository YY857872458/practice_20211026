package com.example.practice.repository;

import com.example.practice.domain.entity.User;

public interface UserRepository {

    User findUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}
