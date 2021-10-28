package com.example.practice.repository.mapper;

import com.example.practice.domain.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from practice_20211026.users where id = #{id}")
    User findUserById(@Param("id") Long id);

    @Insert("insert into practice_20211026.users (name,age,isMale) values (#{name},#{age},#{isMale})")
    void insertUser(User user);

    @Update("update practice_20211026.users set name = #{name}, age = #{age}, isMale = #{isMale} where id = #{id}")
    void updateUser(User user);

    @Delete("delete from practice_20211026.users where id = #{id}")
    void deleteUser(@Param("id") Long id);

    @Select("select * from practice_20211026.users where age < #{age}")
    List<User> getUserUnder(@Param("age") Long age);
}
