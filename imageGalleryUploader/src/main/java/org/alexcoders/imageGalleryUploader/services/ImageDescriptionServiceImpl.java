package org.alexcoders.imageGalleryUploader.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.alexcoders.imageGalleryUploader.models.ImageDescription;
import org.alexcoders.imageGalleryUploader.models.QImageDescription;
import org.alexcoders.imageGalleryUploader.repositories.ImageDescriptionRepository;
import org.alexcoders.imageGalleryUploader.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.mysema.query.types.Predicate;

@Service
public class ImageDescriptionServiceImpl implements ImageDescriptionService {

	@Autowired
	ImageDescriptionRepository imageDescriptionRepository;

	@Autowired
	FileNameUtils fileNameUtils;

	@Autowired
	GridFsTemplate gridFsTemplate;

	@Override
	public void save(List<FileDescription> fileDescriptions) {
		List<String> results = new ArrayList<String>();
		for (FileDescription fileDescription : fileDescriptions) {
			MultipartFile file = fileDescription.getFile();
			String name = fileNameUtils.getFileName(fileDescription);
			if (!file.isEmpty()) {
				if (!fileNameUtils.isExtensionSupported(name)) {
					results.add("Extension is not supported for file " + name + "!");
				} else {

					try {
						byte[] bytes = file.getBytes();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
						stream.write(bytes);
						stream.close();
						gridFsTemplate.store(file.getInputStream(), name);

						ImageDescription imageDescription = new ImageDescription();
						imageDescription.setName(name);
						imageDescription.setAltTag(fileDescription.getAltTag());
						imageDescription.setCaption(fileDescription.getCaption());
						imageDescriptionRepository.save(imageDescription);

					} catch (Exception e) {
						results.add("You failed to upload " + name + " => " + e.getMessage());
					}
				}
			} else {
				results.add("You failed to upload " + name + " because the file was empty.");
			}
		}
	}

	@Override
	public Map<ImageDescription, GridFSDBFile> getImages(List<FileDescription> fileDescriptions) {
		Map<ImageDescription, GridFSDBFile> imageMap = new HashMap<ImageDescription, GridFSDBFile>();
		List<String> filenames = new ArrayList<String>();
		for (FileDescription fileDescription : fileDescriptions) {
			String name = fileNameUtils.getFileName(fileDescription);
			filenames.add(name);
		}
		QImageDescription qImageDescription = QImageDescription.imageDescription;
		Predicate predicate = qImageDescription.name.in(filenames);
		Iterable<ImageDescription> findAllByName = imageDescriptionRepository.findAll(predicate);
		for (ImageDescription imageDescription : findAllByName) {
			Query query = new Query().addCriteria(Criteria.where("filename").is(imageDescription.getName()));
			GridFSDBFile imageFile = gridFsTemplate.findOne(query );
			imageMap.put(imageDescription, imageFile);
		}
		return imageMap;
	}

}
