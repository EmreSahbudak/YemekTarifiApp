package com.bilgeadam.service;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.ForgotPasswordMailResponseDto;
import com.bilgeadam.dto.response.RegisterAuthResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.entity.enums.EStatus;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IEmailManager;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.JwtTokenProvider;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final IUserManager userManager;
    private final IEmailManager emailManager;

    public AuthService(IAuthRepository authRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, IUserManager userManager, IEmailManager emailManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userManager = userManager;
        this.emailManager = emailManager;
    }

    public RegisterAuthResponseDto register(RegisterAuthRequestDto dto){
        Auth auth= IAuthMapper.INSTANCE.fromRegisterDtoToAuth(dto);
        if (dto.getPassword().equals(dto.getRepassword())){
            auth.setActivationCode(CodeGenerator.generateCode());
            auth.setPassword(passwordEncoder.encode(dto.getPassword()));
            save(auth);
            userManager.createUser(IAuthMapper.INSTANCE.fromAuthToNewCreateUserDto(auth));
        }else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }
        RegisterAuthResponseDto responseDto=IAuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
        return responseDto;
    }

    public Boolean activateStatus(ActivateRequestDto dto){
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }else if (auth.get().getActivationCode().equals(dto.getActivateCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            update(auth.get());
            userManager.activateStatus(auth.get().getAuthId());
            return true;
        }
        throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
    }
    public String login(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByUsername(dto.getUsername());
        if (auth.isEmpty() || ! passwordEncoder.matches(dto.getPassword(),auth.get().getPassword())){
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        } else if (!auth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
        return jwtTokenProvider.createToken(auth.get().getAuthId(), auth.get().getRole())
                .orElseThrow(() -> {throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
                });
    }
    public Boolean forgotPassword(String email,String username){
        Optional<Auth> auth = authRepository.findOptionalByEmail(email);
        if (auth.get().getStatus().equals(EStatus.ACTIVE)){
            if (auth.get().getUsername().equals(username)){
                //random password variable
                String randomPassword = UUID.randomUUID().toString();
                auth.get().setPassword(passwordEncoder.encode(randomPassword));
                save(auth.get());
                ForgotPasswordMailResponseDto dto= ForgotPasswordMailResponseDto.builder()
                        .password(randomPassword)
                        .email(email)
                        .build();
                emailManager.forgotPasswordMail(dto);

                UserProfileChangePasswordRequestDto userProfileChangePasswordRequestDto = UserProfileChangePasswordRequestDto.builder()
                        .authId(auth.get().getAuthId())
                        .password(auth.get().getPassword())
                        .build();
                userManager.forgotPasswordUser(userProfileChangePasswordRequestDto);
                return true;
            }else {
                throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
            }
        }else {
            if (auth.get().getStatus().equals(EStatus.DELETED)){
                throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
            }
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
    }









































}
