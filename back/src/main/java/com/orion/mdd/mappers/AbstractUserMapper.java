package com.orion.mdd.mappers;

import com.orion.mdd.dtos.user.AuthorDto;
import com.orion.mdd.dtos.user.UpdateUserDto;
import com.orion.mdd.models.User;

public abstract class AbstractUserMapper implements EntityMapper<UpdateUserDto, User> {}
