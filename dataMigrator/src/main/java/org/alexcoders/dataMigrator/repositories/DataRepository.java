package org.alexcoders.dataMigrator.repositories;

import org.alexcoders.dataMigrator.models.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CrudRepository<Data, Integer> {

}