package com.example.practice.RepositoryTest;

import com.example.practice.UnitTests;
import com.example.practice.domain.entity.User;
import com.example.practice.repository.UserRepositoryImpl;
import com.example.practice.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryTests{

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    public void should_find_user_by_id(){
        //given
        given(userMapper.findUserById(1L)).willReturn(User.builder()
                .id(1L).age(18L).isMale(false).name("小红").build());

        //when
        final User userById = userRepository.findUserById(1L);

        //then
        assertThat(userById.getAge()).isEqualTo(18L);
        assertThat(userById.getName()).isEqualTo("小红");
    }

    @Test
    public void should_add_user(){
        //given

        //when

        //then
    }


}
