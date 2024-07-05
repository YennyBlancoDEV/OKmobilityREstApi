package com.rest.api.operations.persistence.repositories.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest.api.operations.persistence.entities.OperationEntity;


public interface OperationDAO extends JpaRepository<OperationEntity, Integer> {
	
	@Modifying
	@Query("UPDATE Operation o SET o.status = :status WHERE o.parentTaskId = :id")
	void updateSubtaskStatus(@Param("id") Long id, @Param("status") String status);

	@Modifying
	@Query("UPDATE Operation o SET o.status = :status WHERE o.taskId = :id")
	void updateParentStatus(@Param("id") Long id, @Param("status") String status);

	@Modifying
	@Query("DELETE from Operation o where o.parentTaskId = :id")
	void deleteSubtaskStatus(@Param("parentTaskId") Long parentTaskId);
	                        

}
