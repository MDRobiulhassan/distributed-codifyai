package com.codifyai.distributed.account_service.service;

import com.codifyai.distributed.account_service.dto.auth.AuthResponse;
import com.codifyai.distributed.account_service.dto.auth.LoginRequest;
import com.codifyai.distributed.account_service.dto.auth.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
