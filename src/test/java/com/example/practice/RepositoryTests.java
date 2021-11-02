package com.example.practice;

import com.example.practice.domain.entity.User;
import com.example.practice.repository.UserRepositoryImpl;
import com.example.practice.repository.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RepositoryTests extends UnitTests{

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
        given(userMapper.findUserById(1L)).willReturn(User.builder()
                .id(1L).age(18L).isMale(false).name("小红").build());

        //when
        final User userById = userRepository.findUserById(1L);

        //then
        assertThat(userById.getAge()).isEqualTo(18L);
        assertThat(userById.getName()).isEqualTo("小红");
    }


}
