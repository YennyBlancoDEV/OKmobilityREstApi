package com.rest.api.operations.services.bussneslogics;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.rest.api.operations.persistence.entities.OperationEntity;

//Interface that declared the basic operation for the operations table
public interface IOperationService {
	
	public OperationEntity createOperation (OperationEntity operation);
    
    public OperationEntity updateOperation(int Id, OperationEntity newOperation);
    
    public List<OperationEntity> listAllOperations();
    
    public HashMap<String, String> deleteOperation (int operationId);
    
}


