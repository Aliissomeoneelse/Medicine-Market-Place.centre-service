package com.example.MedicineMarketPlace.centreservice.service;

import com.example.MedicineMarketPlace.centreservice.dto.RatingDto;
import com.example.MedicineMarketPlace.centreservice.dto.ResponseDto;
import com.example.MedicineMarketPlace.centreservice.repository.RatingRepository;
import com.example.MedicineMarketPlace.centreservice.service.mapper.RatingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    public ResponseDto<RatingDto> create(RatingDto dto) {
        try {
            return ResponseDto.<RatingDto>builder()
                    .data(ratingMapper.toDto(ratingRepository.save(ratingMapper.toEntity(dto))))
                    .message("Rating successful created!")
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<RatingDto>builder()
                    .message("Rating while saving error "+e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<RatingDto> get(Integer id) {
        return ratingRepository.findByRatingIdAndDeletedAtIsNull(id)
                .map(rating -> ResponseDto.<RatingDto>builder()
                        .message("OK")
                        .success(true)
                        .data(ratingMapper.toDto(rating))
                        .build())
                .orElse(ResponseDto.<RatingDto>builder()
                        .message("Rating not found!")
                        .code(-1)
                        .build());
    }

    public ResponseDto<RatingDto> getWithUsers(Integer id) {
        return ratingRepository.findByRatingIdAndDeletedAtIsNull(id)
                .map(rating -> ResponseDto.<RatingDto>builder()
                        .message("OK")
                        .success(true)
                        .data(ratingMapper.toDtoWithUser(rating))
                        .build())
                .orElse(ResponseDto.<RatingDto>builder()
                        .message("Rating not found!")
                        .code(-1)
                        .build());
    }

    public ResponseDto<RatingDto> update(RatingDto dto, Integer id) {
        try {
            return ratingRepository.findByRatingIdAndDeletedAtIsNull(id)
                    .map(rating -> {
                        ratingMapper.update(rating,dto);
                        ratingRepository.save(rating);
                        return ResponseDto.<RatingDto>builder()
                                .message("Rating successful updated")
                                .success(true)
                                .data(ratingMapper.toDto(rating))
                                .build();
                    })
                    .orElse(ResponseDto.<RatingDto>builder()
                            .message("Rating not found!")
                            .code(-1)
                            .build());
        }catch (Exception e){
            return ResponseDto.<RatingDto>builder()
                    .message("Rating while updating error "+e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<RatingDto> delete(Integer id) {
        try {
            return ratingRepository.findByRatingIdAndDeletedAtIsNull(id)
                    .map(rating -> {
                        rating.setDeletedAt(LocalDateTime.now());
                        ratingRepository.save(rating);
                        return ResponseDto.<RatingDto>builder()
                                .message("Rating successful deleted!")
                                .success(true)
                                .data(ratingMapper.toDto(rating))
                                .build();
                    })
                    .orElse(ResponseDto.<RatingDto>builder()
                            .message("Rating not found!")
                            .code(-1)
                            .build());
        }catch (Exception e){
            return ResponseDto.<RatingDto>builder()
                    .message("Rating while deleting error "+e.getMessage() )
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Set<RatingDto>> getRatingByCentreId(Integer id) {
        return null;
    }
}
