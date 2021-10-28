package com.example.practice.web;

import com.example.practice.domain.entity.User;
import com.example.practice.domain.entity.UserV01;
import com.example.practice.domain.entity.UserV02;
import com.example.practice.domain.service.UserService;
import com.example.practice.repository.mapper.UserConvertBasic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/V01/{id}")
    public UserV01 getUserV01(@PathVariable Long id){
        final User user = userService.getUser(id);
        return UserConvertBasic.INSTANCE.toUserV01(user);
    }

    @GetMapping
    public List<UserV02> getUsersUnder(@RequestParam(value = "age") Long age){
        return userService.getUsersUnder(age);
    }
}
