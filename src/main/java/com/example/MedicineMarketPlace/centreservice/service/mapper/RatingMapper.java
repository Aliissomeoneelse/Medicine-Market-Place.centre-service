package com.example.MedicineMarketPlace.centreservice.service.mapper;

import com.example.MedicineMarketPlace.centreservice.client.service.UserClient;
import com.example.MedicineMarketPlace.centreservice.dto.RatingDto;
import com.example.MedicineMarketPlace.centreservice.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    //@Mapping(target = "user", expression = "java()")  //need write  userClient
    public abstract RatingDto toDtoWithUser(Rating rating);

    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    public abstract void update(Rating rating, RatingDto dto);
}