package com.example.MedicineMarketPlace.centreservice.service.mapper;


import com.example.MedicineMarketPlace.centreservice.client.service.UserClient;
import com.example.MedicineMarketPlace.centreservice.dto.RatingDto;
import com.example.MedicineMarketPlace.centreservice.entity.Rating;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public abstract class RatingMapper {

    @Autowired
    protected UserClient userClient;

    public abstract RatingDto toDto(Rating rating);

    @Mapping(target = "ratingId", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Rating toEntity(RatingDto dto);

    @Mapping(target = "user", expression = "java(userClient.getUserByRatingId(rating.getRatingId()).getData())")
    public abstract RatingDto toDtoWithUser(Rating rating);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Rating rating, RatingDto dto);
}