package org.alexcoders.groupProvider.repositories;
import org.alexcoders.groupProvider.models.GroupProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
@RepositoryRestResource(collectionResourceRel = "groupProviderRepository", path = "groupProviderRepository")
public interface GroupProviderRepository extends CrudRepository<GroupProvider, Long> {
 
	
}