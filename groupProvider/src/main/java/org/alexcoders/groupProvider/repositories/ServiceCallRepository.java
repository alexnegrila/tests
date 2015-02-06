package org.alexcoders.groupProvider.repositories;
import java.util.List;

import org.alexcoders.groupProvider.models.ServiceCall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
@RepositoryRestResource(collectionResourceRel = "serviceCallRepository", path = "serviceCallRepository")
public interface ServiceCallRepository extends CrudRepository<ServiceCall, String> {

	@Query("SELECT sc FROM ServiceCall sc INNER JOIN sc.serviceParent s INNER JOIN s.groupParent g  WHERE g.id = :groupId AND LOWER(s.id) = LOWER(:serviceId)")
	List<ServiceCall> findAll(@Param("groupId") Long groupId,@Param("serviceId") String serviceId);
 
	
}