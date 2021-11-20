package com.example.practice.MapperTest;

import com.example.practice.PersistenceTests;
import com.example.practice.domain.entity.User;
import com.example.practice.repository.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.assertj.db.type.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;

public class MapperTests extends PersistenceTests {
    @Autowired
    private UserMapper userMapper;

    @AfterEach
    public void tearDown(){
        userMapper.deleteUser(999L);
    }

    @Test
    @Sql("/sql/should_query_user_by_id.sql")
    void should_query_user_by_id(){
        final User user = userMapper.findUserById(999L);

        Assertions.assertThat(user.getName().equals("query_name"));
        Assertions.assertThat(user.getAge().equals(999L));
        Assertions.assertThat(user.getIsMale()).isTrue();
    }

    @Test
    void should_insert_user_successfully(){
        User newUser = User.builder().id(999L).name("yy").age(10L).isMale(true).build();
        userMapper.insertUser(newUser);
        Request sql = new Request(getDataSource(), "select * from users where id=999");

        assertThat(sql).hasNumberOfRows(1)
                .row()
                .column("name").value().isEqualTo("yy")
                .column("age").value().isEqualTo(10L)
                .column("isMale").value().isTrue();
    }

    @Test
    @Sql("/sql/should_update_user_successfully.sql")
    void should_update_user_successfully(){
        User newUser = User.builder().id(999L).name("test_name_update").age(998L).isMale(false).build();
        userMapper.updateUser(newUser);

        Request sql = new Request(getDataSource(),"select * from users where id = 999");

        assertThat(sql).hasNumberOfRows(1)
                .row()
                .column("name").value().isEqualTo("test_name_update")
                .column("age").value().isEqualTo(998L)
                .column("isMale").value().isEqualTo(false);
    }

    @Test
    @Sql("/sql/should_delete_user_successfully.sql")
    void should_delete_user_successfully(){
        userMapper.deleteUser(999L);

        Request sql = new Request(getDataSource(),"select * from users where id = 999");

        assertThat(sql).hasNumberOfRows(0);
    }

    @Test
    @Sql("/sql/should_get_users_under_age_successfully.sql")
    void should_get_users_under_age_successfully(){
        final List<User> usersUnder1000 = userMapper.getUserUnder(1000L);

        Assertions.assertThat(usersUnder1000.size()).isEqualTo(3);
        Assertions.assertThat(usersUnder1000.get(usersUnder1000.size()-1).getId()).isEqualTo(999L);
        Assertions.assertThat(usersUnder1000.get(usersUnder1000.size()-1).getAge()).isEqualTo(999L);
    }
}
