package com.rest.api.operations.rest.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.operations.persistence.entities.OperationEntity;
import com.rest.api.operations.persistence.entities.UserEntity;
import com.rest.api.operations.persistence.repositories.DAO.OperationDAO;
import com.rest.api.operations.security.services.IAuthService;
import com.rest.api.operations.services.bussneslogic.implementation.OperationServiceImpl;
import com.rest.api.operations.services.bussneslogics.IOperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//Class With the HTTP Methods - API end points to expose to the  from end
@RestController
@RequestMapping("/operation")
public class OperationsRestAPI {
	

    @Autowired
    IOperationService operationService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    
    @PostMapping("/create")
    public ResponseEntity<OperationEntity> createOperation(@RequestBody OperationEntity operation) { // change the type of request here
    	
    	
    	LOGGER.info("START Rest createOperation  - create new an operation task ");
    		
		try {	
			
		    	OperationEntity createdOperationTask = operationService.createOperation(operation);
		    	
		    	LOGGER.info("END Rest createOperation() - Operation Task created successfully");
		    	
		        return new ResponseEntity<>(createdOperationTask, HttpStatus.CREATED);
		        
		    } catch (Exception e) {
		    	
		    	LOGGER.error("Error while creating a new operation : {}", e.getMessage());
		    	
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	
    }
	
	
    //Updates parents and child tasks on the  operation
    @PutMapping("/update/{operationId}")
    public ResponseEntity<OperationEntity> updateOperation(@PathVariable int operationId, @RequestBody OperationEntity newOperation) {
    	
    	LOGGER.info("START Rest updateOperation  - update main operation record and parent and childs ");
    	
    	try {
        	
        	OperationEntity updatedOperation = operationService.updateOperation(operationId, newOperation);
        			
            if (updatedOperation != null) {
            	
            	LOGGER.info("ENDED Rest updateOperation  - task was completed and other task completed if aply ");
            	
                return new ResponseEntity<>(updatedOperation, HttpStatus.OK);
            }
            
            LOGGER.info("ENDED Rest updateOperation  - the taks to be updated was not found ");
            
            return ResponseEntity.notFound().build();
            
        } catch (Exception e) {
        	
        	LOGGER.error("ENDED Rest updateOperation  - the taks to be updated was not found ");
        	
        	LOGGER.error("Error while updating operation task records : {}", e.getMessage());
        	  
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/listAll")
    private ResponseEntity<List<OperationEntity>> getAllUsers(){
    	LOGGER.info("START Rest list all operation task records"  );
        return new ResponseEntity<>(operationService.listAllOperations(), HttpStatus.OK);
    }
    
    
    @DeleteMapping("/delete/{operationId}")
    public ResponseEntity<HashMap<String, String>> deleteUser(@PathVariable int operationId) {
    	
    	LOGGER.info("START Rest deleteOperation  - delete main operation record and parent and childs ");
    	
        try {
        	
            HashMap<String, String> response = operationService.deleteOperation(operationId);
            
            LOGGER.info("ENDS Rest deleteOperation  - delete main operation record and parent and childs ");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    

	
}
