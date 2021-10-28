package com.example.practice.repository.mapper;


import com.example.practice.domain.entity.User;
import com.example.practice.domain.entity.UserV01;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvertBasic {

    UserConvertBasic INSTANCE = Mappers.getMapper(UserConvertBasic.class);

    @Mappings(@Mapping(source = "isMale",target = "sex"))
    UserV01 toUserV01(User user);
}
