package com.example.practice.ApiTest;

import com.example.practice.domain.entity.User;
import com.example.practice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository; //mapper?

    @AfterEach
    public void tearDown(){
        userRepository.deleteUser(999L);
    }

    @Test
    public void should_add_user(){
        //given
        User testUser = User.builder().id(999L).name("test").age(999L).isMale(true).build();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(testUser,headers);

        //when
        final ResponseEntity<Object> addEntity = restTemplate.postForEntity("/users", request, null);

        //then
        Assertions.assertEquals(HttpStatus.OK,addEntity.getStatusCode());

        //不整洁？
        final ResponseEntity<User> entity = restTemplate.getForEntity("/users/{id}", User.class, 999L);
        Assertions.assertEquals(testUser,entity.getBody());
        Assertions.assertEquals(MediaType.APPLICATION_JSON,entity.getHeaders().getContentType());
    }

    @Test
    @Sql("/sql/insert_new_user.sql")
    public void should_get_user(){
        final ResponseEntity<User> entity = restTemplate.getForEntity("/users/{id}", User.class, 999L);

        Assertions.assertEquals(HttpStatus.OK,entity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON,entity.getHeaders().getContentType());
        Assertions.assertEquals("test", Objects.requireNonNull(entity.getBody()).getName());
        Assertions.assertEquals(999L, Objects.requireNonNull(entity.getBody()).getAge());
        Assertions.assertEquals(true, Objects.requireNonNull(entity.getBody()).getIsMale());
    }

//    @Test
//    @Sql("/sql/insert_new_user.sql")
//    public void should_update_user(){
//        HttpHeaders headers = new HttpHeaders();
//        final User userUpdated = User.builder().id(999L).name("test-updated").age(1000L).isMale(false).build();
//        HttpEntity<User> request = new HttpEntity<>(userUpdated,headers);
//        restTemplate.put("/users",request);
//
//    }

}
