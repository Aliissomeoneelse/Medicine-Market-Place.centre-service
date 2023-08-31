package com.example.MedicineMarketPlace.centreservice.controller;

import com.example.MedicineMarketPlace.centreservice.dto.CentreDto;
import com.example.MedicineMarketPlace.centreservice.dto.ResponseDto;
import com.example.MedicineMarketPlace.centreservice.service.CentreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(("centre"))
@RequiredArgsConstructor
public class CentreController {
    private final CentreService centreService;

    @PostMapping("/create")
    public ResponseDto<CentreDto> create(@Valid @RequestBody CentreDto dto) {
        return this.centreService.create(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseDto<CentreDto> get(@PathVariable(value = "id") Integer id) {
        return this.centreService.get(id);
    }

    @GetMapping("/get-with-rating/{id}")
    public ResponseDto<CentreDto> getWithRating(@PathVariable(value = "id") Integer id) {
        return this.centreService.getWithRating(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<CentreDto> update(@RequestBody CentreDto dto, @PathVariable(value = "id") Integer id) {
        return this.centreService.update(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<CentreDto> delete(@PathVariable(value = "id") Integer id) {
        return this.centreService.delete(id);
    }

    @GetMapping("/get-by-doctor/{id}")
    public ResponseDto<Set<CentreDto>> getCentreByDoctor(@PathVariable(value = "id") Integer id) {
        return this.centreService.getCentreByDoctor(id);
    }

    @GetMapping("/get-by-medical-service/{id}")
    public ResponseDto<Set<CentreDto>> getCentreByMedicalService(@PathVariable(value = "id") Integer id) {
        return this.centreService.getCentreByMedicalService(id);
    }
}