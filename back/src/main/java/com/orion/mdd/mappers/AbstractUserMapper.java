package com.orion.mdd.mappers;

import com.orion.mdd.dtos.user.UpdateUserDto;
import com.orion.mdd.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractUserMapper implements EntityMapper<UpdateUserDto, User> {}
