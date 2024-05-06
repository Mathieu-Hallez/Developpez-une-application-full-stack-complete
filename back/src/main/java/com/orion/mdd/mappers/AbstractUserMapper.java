package com.orion.mdd.mappers;

import com.orion.mdd.dtos.user.AuthorDto;
import com.orion.mdd.dtos.user.UpdateUserDto;
import com.orion.mdd.models.User;
import com.orion.mdd.services.CommentService;
import com.orion.mdd.services.TopicService;
import com.orion.mdd.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractUserMapper implements EntityMapper<UpdateUserDto, User> {}
