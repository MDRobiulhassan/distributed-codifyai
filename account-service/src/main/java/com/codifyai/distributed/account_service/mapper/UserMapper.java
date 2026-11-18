package com.codifyai.distributed.account_service.mapper;

import com.codifyai.distributed.account_service.dto.auth.SignupRequest;
import com.codifyai.distributed.account_service.dto.auth.UserProfileResponse;
import com.codifyai.distributed.account_service.entity.User;
import com.codifyai.distributed.common_lib.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);
    UserProfileResponse toUserProfileResponse(User user);
    UserDto toUserDto(User user);
}
