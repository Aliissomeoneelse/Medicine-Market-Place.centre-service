package com.example.MedicineMarketPlace.centreservice.controller;

import com.example.MedicineMarketPlace.centreservice.dto.RatingDto;
import com.example.MedicineMarketPlace.centreservice.dto.ResponseDto;
import com.example.MedicineMarketPlace.centreservice.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("rating")
@RequiredArgsConstructor
public class RatingController {
private final RatingService ratingService;
@PostMapping("/create")
public ResponseDto<RatingDto> create(@Valid @RequestBody RatingDto dto){
    return this.ratingService.create(dto);
}
@GetMapping("/get/{id}")
public ResponseDto<RatingDto> get(@PathVariable(value = "id") Integer id){
    return this.ratingService.get(id);
}
@GetMapping("/get-with-user/{id}")
public ResponseDto<RatingDto>getWithUsers(@PathVariable(value = "id") Integer id){
    return this.ratingService.getWithUsers(id);
}
@PutMapping("/update/{id}")
public ResponseDto<RatingDto> update(@RequestBody RatingDto dto,@PathVariable(value = "id") Integer id){
    return this.ratingService.update(dto,id);
}
@DeleteMapping("/delete/{id}")
public ResponseDto<RatingDto> delete(@PathVariable(value = "id") Integer id){
    return this.ratingService.delete(id);
}
}
