package com.example.MedicineMarketPlace.centreservice.service;

import com.example.MedicineMarketPlace.centreservice.dto.CentreDto;
import com.example.MedicineMarketPlace.centreservice.dto.ResponseDto;
import com.example.MedicineMarketPlace.centreservice.repository.CentreRepository;
import com.example.MedicineMarketPlace.centreservice.service.mapper.CentreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CentreService {
    private final CentreMapper centreMapper;
    private final CentreRepository centreRepository;

    public ResponseDto<CentreDto> create(CentreDto dto) {
        try {
            return ResponseDto.<CentreDto>builder()
                    .message("Centre successful created!")
                    .success(true)
                    .data(centreMapper.toDto(centreRepository.save(centreMapper.toEntity(dto))))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CentreDto>builder()
                    .message("Centre while saving error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CentreDto> get(Integer id) {
        return centreRepository.findByCentreIdAndDeletedAtIsNull(id)
                .map(centre -> ResponseDto.<CentreDto>builder()
                        .message("OK")
                        .success(true)
                        .data(centreMapper.toDto(centre))
                        .build())
                .orElse(ResponseDto.<CentreDto>builder()
                        .message("Centre not found!")
                        .code(-1)
                        .build());
    }

    public ResponseDto<CentreDto> getWithRating(Integer id) {
        return centreRepository.findByCentreIdAndDeletedAtIsNull(id)
                .map(centre -> ResponseDto.<CentreDto>builder()
                        .message("OK")
                        .success(true)
                        .data(centreMapper.toDtoWithRating(centre))
                        .build())
                .orElse(ResponseDto.<CentreDto>builder()
                        .message("Centre not found!")
                        .code(-1)
                        .build());
    }

    public ResponseDto<CentreDto> update(CentreDto dto, Integer id) {
        try {
            return centreRepository.findByCentreIdAndDeletedAtIsNull(id)
                    .map(centre -> {
                        centreMapper.update(centre, dto);
                        centreRepository.save(centre);
                        return ResponseDto.<CentreDto>builder()
                                .message("Centre successful updated!")
                                .success(true)
                                .data(this.centreMapper.toDto(centre))
                                .build();
                    })
                    .orElse(ResponseDto.<CentreDto>builder().build());
        } catch (Exception e) {
            return ResponseDto.<CentreDto>builder()
                    .message("Centre while updating error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CentreDto> delete(Integer id) {
        try {
            return centreRepository.findByCentreIdAndDeletedAtIsNull(id)
                    .map(centre -> {
                        centre.setDeletedAt(LocalDateTime.now());
                        centreRepository.save(centre);
                        return ResponseDto.<CentreDto>builder()
                                .message("Centre successful deleted!")
                                .success(true)
                                .data(this.centreMapper.toDto(centre))
                                .build();
                    })
                    .orElse(ResponseDto.<CentreDto>builder().build());
        } catch (Exception e) {
            return ResponseDto.<CentreDto>builder()
                    .message("Centre while deleting error " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Set<CentreDto>> getCentreByDoctor(Integer id) {
        return ResponseDto.<Set<CentreDto>>builder()
                .message("Ok")
                .success(true)
                .data(centreRepository.findAllByDoctorIdAndDeletedAtIsNull(id).stream().map(centreMapper::toDto).collect(Collectors.toSet()))
                .build();
    }

    public ResponseDto<Set<CentreDto>> getCentreByMedicalService(Integer id) {
        return ResponseDto.<Set<CentreDto>>builder()
                .message("Ok")
                .success(true)
                .data(centreRepository.findAllByMedicalServiceIdAndDeletedAtIsNull(id).stream().map(centreMapper::toDto).collect(Collectors.toSet()))
                .build();
    }
}