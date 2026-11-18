package com.codifyai.distributed.account_service.serviceimpl;

import com.codifyai.distributed.account_service.dto.auth.AuthResponse;
import com.codifyai.distributed.account_service.dto.auth.LoginRequest;
import com.codifyai.distributed.account_service.dto.auth.SignupRequest;
import com.codifyai.distributed.account_service.entity.User;
import com.codifyai.distributed.account_service.mapper.UserMapper;
import com.codifyai.distributed.account_service.repository.UserRepository;
import com.codifyai.distributed.account_service.service.AuthService;
import com.codifyai.distributed.common_lib.error.BadRequestException;
import com.codifyai.distributed.common_lib.security.AuthUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User Already Exists with username: " + request.username());
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        String token = authUtil.generateAccessToken(userMapper.toUserDto(user));
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) authentication.getPrincipal();
        assert user != null;
        String token = authUtil.generateAccessToken(userMapper.toUserDto(user));
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }
}
