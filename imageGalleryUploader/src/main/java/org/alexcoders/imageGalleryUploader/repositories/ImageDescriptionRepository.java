package org.alexcoders.imageGalleryUploader.repositories;

import java.util.List;

import org.alexcoders.imageGalleryUploader.models.ImageDescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ImageDescriptionRepository extends MongoRepository<ImageDescription, String>, QueryDslPredicateExecutor<ImageDescription> {

	public List<ImageDescription> findByName(String name);
	
	public List<ImageDescription> findByNameAllIgnoringCase(List<String> names);
}
