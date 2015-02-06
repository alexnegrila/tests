package org.alexcoders.groupProvider.repositories;
import org.alexcoders.groupProvider.models.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
@RepositoryRestResource(collectionResourceRel = "groupRepository", path = "groupRepository")
public interface GroupRepository extends CrudRepository<Group, Long> {
 
	
}