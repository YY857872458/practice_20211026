package com.example.practice.ServiceTest;

import com.example.practice.domain.entity.User;
import com.example.practice.domain.service.UserService;
import com.example.practice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTests {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private final User user1 = User.builder()
            .id(999L).name("test").age(1L).isMale(true).build();

    @Test
    void should_find_user_by_id(){
        //given
        given(userRepository.findUserById(999L)).willReturn(user1);

        //when
        final User user = userService.getUser(999L);

        //then
        Assertions.assertThat(user.getAge()).isEqualTo(1L);
        Assertions.assertThat(user.getName()).isEqualTo("test");
        Assertions.assertThat(user.getIsMale()).isTrue();
    }

    @Test
    void should_add_user(){
        //given

        //when

        //then
    }
}
