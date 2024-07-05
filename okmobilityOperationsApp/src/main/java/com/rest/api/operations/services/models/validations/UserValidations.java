package com.rest.api.operations.services.models.validations;

import com.rest.api.operations.persistence.entities.UserEntity;
import com.rest.api.operations.services.models.dtos.ResponseDTO;

//Class where validations are perform 
public class UserValidations {

    public ResponseDTO validate(UserEntity user){
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if (user.getFirstName() == null || user.getFirstName().length() < 3 || user.getFirstName().length() > 15){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage(" firstName cant be null, it has to be between 3 to 15 caracters");
        }

        if (user.getLastName() == null || user.getLastName().length() < 3 || user.getLastName().length() > 30){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("lastName cant be null, it has to be between 3 to 30 caracters ");
        }

        if (
            user.getEmail() == null ||
            !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("email is incorrect");
        }

        if(
                user.getPassword() == null ||
                        !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("password has to be between 8 and 16 caracteres and with special characters");
        }

        return response;
    }
}
