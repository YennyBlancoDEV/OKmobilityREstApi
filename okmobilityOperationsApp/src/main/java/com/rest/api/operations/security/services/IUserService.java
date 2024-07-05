package com.rest.api.operations.security.services;

import com.rest.api.operations.persistence.entities.UserEntity;

import java.util.List;

public interface IUserService {

    public List<UserEntity> findAllUsers();
}
