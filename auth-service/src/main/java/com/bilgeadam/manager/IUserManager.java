package com.bilgeadam.manager;

import com.bilgeadam.dto.request.ManagerRegisterAddressRequestDto;
import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UserProfileChangePasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constant.ApiUrls.DELETE_BY_ID;
import static com.bilgeadam.constant.ApiUrls.FORGOT_PASSWORD;

@FeignClient(url = "http://localhost:8090/api/v1/user", name = "auth-user")
public interface IUserManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);

    @GetMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);

    @DeleteMapping(DELETE_BY_ID + "/{authId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long authId);

    @PostMapping("/register-address")
    public ResponseEntity<Boolean> registerAddress(@RequestBody ManagerRegisterAddressRequestDto dto);


    //forgotpassword metodunun userserviviceyede gitmesi için
    @PutMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPasswordUser(@RequestBody UserProfileChangePasswordRequestDto dto);


}
