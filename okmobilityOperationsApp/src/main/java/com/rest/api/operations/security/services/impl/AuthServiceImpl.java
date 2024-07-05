package com.rest.api.operations.security.services.impl;

import com.rest.api.operations.services.bussneslogic.implementation.OperationServiceImpl;
import com.rest.api.operations.services.models.dtos.LoginDTO;
import com.rest.api.operations.persistence.entities.UserEntity;
import com.rest.api.operations.persistence.repositories.DAO.UserDAO;
import com.rest.api.operations.security.services.IAuthService;
import com.rest.api.operations.security.services.IJWTUtilityService;
import com.rest.api.operations.services.models.dtos.ResponseDTO;
import com.rest.api.operations.services.models.validations.UserValidations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidations userValidations;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    
    @Override
    public ResponseDTO register(UserEntity user) throws Exception {
        try {
        	//create a response object after validating the user details
            ResponseDTO response = userValidations.validate(user);
            
            //List all user to verify in DB if this user is already existing
            List<UserEntity> getAllUsers = userRepository.findAll();

            if (response.getNumOfErrors() > 0){
                return response;
            }

            for (UserEntity repeatFields : getAllUsers) {
                if (repeatFields != null) {
                    response.setMessage("User already exists!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created successfully!");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception {
        try {
        	
        	LOGGER.info(" AuthService -  request for loggin in progress ");
        	
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(loginRequest.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "User not registered!");
                return jwt;
            }
            
            // if password for this user is correct generate logging user and generate token
            if (verifyPassword(loginRequest.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Authentication failed, your provided token information has been expired or not exists");
            }
            
            LOGGER.info(" AuthService -  ended request for loggin in progress ");
            
            return jwt;
            
         //exception handling 
        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    


    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
