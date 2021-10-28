package com.example.practice.repository.mapper;


import com.example.practice.domain.entity.User;
import com.example.practice.domain.entity.UserV01;
import com.example.practice.domain.entity.UserV02;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvertBasic {

    UserConvertBasic INSTANCE = Mappers.getMapper(UserConvertBasic.class);

    @Mappings(@Mapping(source = "isMale",target = "sex"))
    UserV01 toUserV01(User user);

    List<UserV02> toUserV02(List<User> users);
}
