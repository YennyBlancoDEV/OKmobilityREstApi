package com.rest.api.operations.security.services.impl;

import com.rest.api.operations.persistence.entities.UserEntity;
import com.rest.api.operations.persistence.repositories.DAO.UserDAO;
import com.rest.api.operations.security.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDAO userRepository;

    @Override
    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }
}
