package com.example.MedicineMarketPlace.centreservice.client.service;

import com.example.MedicineMarketPlace.centreservice.client.dto.UserDto;
import com.example.MedicineMarketPlace.centreservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "user-service", path = "/user-service/user")
public interface UserClient {

    @GetMapping(value = "/get-user-by-rating/{id}")
    ResponseDto<Set<UserDto>> getUserByRatingId(@PathVariable("id") Integer id);

}
