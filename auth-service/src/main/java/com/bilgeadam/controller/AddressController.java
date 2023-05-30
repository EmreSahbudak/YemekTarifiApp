package com.bilgeadam.controller;

import com.bilgeadam.dto.request.RegisterAddressRequestDto;
import com.bilgeadam.entity.Address;
import com.bilgeadam.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bilgeadam.constant.ApiUrls.*;
@RestController
@RequestMapping(ADDRESS)
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Address>> findAllAddress(){
        return ResponseEntity.ok(addressService.findAll());
    }
    @PostMapping(REGISTER_ADDRESS)
    public ResponseEntity<Boolean> registerAddress(@RequestBody @Valid RegisterAddressRequestDto dto){
        return ResponseEntity.ok(addressService.registerAddress(dto));
    }
}
