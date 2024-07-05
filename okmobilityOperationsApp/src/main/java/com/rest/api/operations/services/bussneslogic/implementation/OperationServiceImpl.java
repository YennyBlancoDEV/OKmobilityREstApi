package com.rest.api.operations.services.bussneslogic.implementation;

import com.rest.api.operations.persistence.entities.OperationEntity;
import com.rest.api.operations.persistence.repositories.DAO.OperationDAO;
import com.rest.api.operations.services.bussneslogics.IOperationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements IOperationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Autowired
    OperationDAO operationDAO;

    @Override
    public OperationEntity createOperation(OperationEntity operation) {
        try {
        	
        	LOGGER.info("creating a new operation task ");
        	
            return operationDAO.save(operation);
            
        } catch (Exception e) {
            LOGGER.error("Error while creating a new operation : {}", e.getMessage());
            throw new RuntimeException("Error creating operation");
        }
    }
    
    @Override
    public List<OperationEntity> listAllOperations() {
        try {
            return operationDAO.findAll();
            
        } catch (Exception e) {
            LOGGER.error("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }
    

	@Override
	public OperationEntity updateOperation(int id, OperationEntity newOperation) {
		try {
			
        	LOGGER.info(" Updating a operation task biz logic ");
        	
        	OperationEntity existingOperation = operationDAO.findById(id).orElse(null);
        	
        	
        	if (existingOperation != null) {
        		
        		//update status of the operation task
        		existingOperation.setTaskStatus(newOperation.getTaskStatus());
        		operationDAO.save(existingOperation);
        		
        		
        		if ( isParent (newOperation) & isCompleted(existingOperation)) {
				
        		// update all subtask to status completed too
				operationDAO.updateSubtaskStatus(existingOperation.getParentTaskId(), existingOperation.getTaskStatus());
				
				return operationDAO.save(existingOperation);
				
        		} 
        		//check all task with the same parent task and verify if completed too,then close parent too'
        		else{
				// pending to handle check sub child of a parent task to update status to completed too if apply.
        			if (allSubChildtaskAreCompleted() & isCompleted(existingOperation) ) {
        				
        				operationDAO.updateSubtaskStatus(existingOperation.getParentTaskId(), existingOperation.getTaskStatus());
        				operationDAO.updateParentStatus(existingOperation.getParentTaskId(), existingOperation.getTaskStatus());	
        			}
        		
        		return operationDAO.save(existingOperation);
        		}
        	}
        		
            
            throw new RuntimeException("Operation task  not found");
            
        } catch (Exception e) {
        	 LOGGER.error("Error while updating operation: {}", e.getMessage());
             throw new RuntimeException("Error updating operation");
        }

		
	}
    

    @Override
    public HashMap<String, String> deleteOperation (int operationId) {
    	
    	LOGGER.info("Deleting an operation task in progress ");
    	
        try {
            HashMap<String, String> response = new HashMap<>();
            
            OperationEntity deletedOperation = operationDAO.findById(operationId).orElse(null);
            
            if (deletedOperation != null) {
	    	
	    		if ( isParent (deletedOperation) ) {
				
	    		// First delete all subtask 
				operationDAO.deleteSubtaskStatus(deletedOperation.getParentTaskId());
				
	    		} 
	    		
	    		response.put("message", "Operation deleted succesfully!");
	            operationDAO.deleteById(operationId);
	            return response;
            }
           

        	response.put("message", "Deleate Operation fail, task not found!");
            return response;
            
        } catch (Exception e) {
            LOGGER.error("Error while deleting user: {}", e.getMessage());
            throw new RuntimeException("Error deleting user");
        }
    }

    
	//Additional validation operations to simplify code
	private boolean isParent (OperationEntity task) {
		// We identify a parent task by ParentTaskId = 0.
		if (task.getParentTaskId() == 0) {
			return true;
		}
		return false;
	}
	
	
	private boolean isCompleted (OperationEntity task) {
		// We identify a parent task by ParentTaskId = 0.
		if (task.getTaskStatus() == "COMPLETED") {
			return true;
		}
		return false;
	}

	
	private boolean allSubChildtaskAreCompleted() {
		// TODO Auto-generated method stub
		return false;
	}

}
