package com.bilgeadam.controller;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterAuthRequestDto;
import com.bilgeadam.dto.response.RegisterAuthResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterAuthResponseDto> register(@RequestBody @Valid RegisterAuthRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> login(LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }
    //forgotpassword
    @PostMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword(String email, String username){
        return ResponseEntity.ok(authService.forgotPassword(email, username));
    }


}
