package com.orion.mdd.mappers;

import com.orion.mdd.dtos.AuthorDto;
import com.orion.mdd.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class AuthorMapper implements EntityMapper<AuthorDto, User> {}
