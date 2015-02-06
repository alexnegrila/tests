package org.alexcoders.groupProvider.repositories;
import java.util.List;

import org.alexcoders.groupProvider.models.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
@RepositoryRestResource(collectionResourceRel = "serviceRepository", path = "serviceRepository")
public interface ServiceRepository extends CrudRepository<Service, String> {

	@Query("SELECT s FROM Service s JOIN s.groupParent g  WHERE g.id = :groupId AND LOWER(s.id) = LOWER(:serviceId)")
	List<Service> findOne(@Param("groupId") Long groupId,@Param("serviceId") String serviceId);

	@Query("SELECT s FROM Service s JOIN s.groupParent g  WHERE g.id = :groupId")
	List<Service> findAll(@Param("groupId") Long groupId);
 
	
}