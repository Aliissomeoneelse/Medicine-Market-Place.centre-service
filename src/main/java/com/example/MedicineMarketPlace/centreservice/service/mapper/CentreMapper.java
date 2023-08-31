package com.example.MedicineMarketPlace.centreservice.service.mapper;

import com.example.MedicineMarketPlace.centreservice.dto.CentreDto;
import com.example.MedicineMarketPlace.centreservice.entity.Centre;
import com.example.MedicineMarketPlace.centreservice.service.RatingService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public abstract class CentreMapper {
    @Autowired
    protected RatingService ratingService;
    public abstract CentreDto toDto(Centre centre);

    @Mapping(target = "centreId", ignore = true)
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Centre toEntity(CentreDto dto);

    @Mapping(target = "rating", expression = "java(ratingService.getRatingByCentreId(centre.getCentreId()).getData())")
    public abstract CentreDto toDtoWithRating(Centre centre);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Centre centre, CentreDto dto);
}
