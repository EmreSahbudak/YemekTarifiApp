package com.bilgeadam.manager;

import com.bilgeadam.dto.response.UserProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "post-user",
        url = "http://localhost:8090/api/v1/user"
)
public interface IUserProfileManager {

    @GetMapping("/find-by-user-id/{authId}")
    public ResponseEntity<UserProfileResponseDto> findByAuthId(@PathVariable Long authId);
}
