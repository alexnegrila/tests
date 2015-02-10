package org.alexcoders.imageGalleryUploader.services;

import java.util.List;
import java.util.Map;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.alexcoders.imageGalleryUploader.models.ImageDescription;

import com.mongodb.gridfs.GridFSDBFile;

public interface ImageDescriptionService {

	void save(List<FileDescription> fileDescriptions);

	Map<ImageDescription, GridFSDBFile> getImages(List<FileDescription> fileDescriptions);

}
