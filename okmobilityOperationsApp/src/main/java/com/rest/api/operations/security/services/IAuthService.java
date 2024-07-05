package com.rest.api.operations.security.services;

import com.rest.api.operations.services.models.dtos.LoginDTO;
import com.rest.api.operations.persistence.entities.UserEntity;
import com.rest.api.operations.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    public ResponseDTO register(UserEntity user) throws Exception;
}
