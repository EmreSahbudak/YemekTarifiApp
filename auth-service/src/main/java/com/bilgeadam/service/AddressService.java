package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterAddressRequestDto;
import com.bilgeadam.entity.Address;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.IAddressRepository;
import com.bilgeadam.utility.JwtTokenProvider;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService extends ServiceManager<Address,Long> {

    private final IAddressRepository addressRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserManager userManager;

    public AddressService(IAddressRepository addressRepository, JwtTokenProvider jwtTokenProvider, IUserManager userManager) {
        super(addressRepository);
        this.addressRepository = addressRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userManager = userManager;
    }
    public Boolean registerAddress(RegisterAddressRequestDto dto){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isPresent()){
            Address address=IAuthMapper.INSTANCE.fromRegisterAddressDtoToAddress(dto);
            address.setAuthId(authId.get());
            save(address);
            userManager.registerAddress(IAuthMapper.INSTANCE.fromAddressToManagerRegisterAddress(address));
            return true;
        }
        throw new AuthManagerException(ErrorType.INVALID_TOKEN);
    }
}
