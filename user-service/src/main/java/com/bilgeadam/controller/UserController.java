package com.bilgeadam.controller;

import com.bilgeadam.dto.request.ManagerRegisterAddressRequestDto;
import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Hidden //metodu swaggerda gizler cunku bu tarafta kullnılcak bir metot değil bu zaten
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
    @GetMapping(ACTIVATE_STATUS + "/{authId}")
    @Hidden
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId){
        return ResponseEntity.ok(userService.activateStatus(authId));
    }
    @PostMapping("/register-address")
    @Hidden
    public ResponseEntity<Boolean> registerAddress(@RequestBody ManagerRegisterAddressRequestDto dto){
        return ResponseEntity.ok(userService.registerAddress(dto));
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @PutMapping(UPDATE)
    public ResponseEntity<User> update(@RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(userService.update(dto));
    }
    @GetMapping("/find-by-user-id/{authId}")
    public ResponseEntity<UserProfileResponseDto> findByUserProfileDto(@PathVariable Long authId){
        return ResponseEntity.ok(userService.findByUserProfileDto(authId));
    }




















}
