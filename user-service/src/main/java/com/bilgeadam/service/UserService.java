package com.bilgeadam.service;

import com.bilgeadam.dto.request.ManagerRegisterAddressRequestDto;
import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UserUpdateRequestDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.enums.EStatus;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.utility.JwtTokenProvider;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,String > {

    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(IUserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Boolean createUser(NewCreateUserRequestDto dto) {
        try {
            save(IUserMapper.INSTANCE.fromCreateDtoToAuth(dto));
            return true;
        }catch (Exception e){
            throw new RuntimeException("Beklenmenmeyen hata ~~ UserService");
        }
    }
    public Boolean activateStatus(Long authId){
        Optional<User> userProfile = userRepository.findOptionalByAuthId(authId);
        if (userProfile.isEmpty()){
            throw new RuntimeException("Auth id bulunamadı");
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;
    }

    public Boolean registerAddress(ManagerRegisterAddressRequestDto dto) {
        Optional<User> userProfile=userRepository.findOptionalByAuthId(dto.getAuthId());
        System.out.println(userProfile);
        if (userProfile.isEmpty()){
            throw new RuntimeException("User bulunamadı");
        }
        //userProfile.get()=IUserMapper.INSTANCE.fromManagerAddressRequestDtoToAuth(dto);
        userProfile.get().setStreet(dto.getStreet());
        userProfile.get().setNeighborhood(dto.getNeighborhood());
        userProfile.get().setCounty(dto.getCounty());
        userProfile.get().setCity(dto.getCity());
        userProfile.get().setApartmentNumber(dto.getApartmentNumber());
        update(userProfile.get());
        return true;
    }

    public User update(UserUpdateRequestDto dto){
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = userRepository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return update(IUserMapper.INSTANCE.fromUpdateDtoToAuth(dto,user.get()));
    }
    public UserProfileResponseDto findByUserProfileDto(Long authId){
        Optional<User> userProfile = userRepository.findOptionalByAuthId(authId);
        System.out.println(userProfile);
        if (userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        UserProfileResponseDto userProfileResponseDto = IUserMapper.INSTANCE.fromUserProfileToResponseDto(userProfile.get());
        return userProfileResponseDto;

    }













































}
