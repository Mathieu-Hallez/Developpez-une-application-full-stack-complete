package com.orion.mdd.mappers;

import com.orion.mdd.dtos.topic.TopicDto;
import com.orion.mdd.models.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AbstractTopicMapper implements EntityMapper<TopicDto, Topic>{}
