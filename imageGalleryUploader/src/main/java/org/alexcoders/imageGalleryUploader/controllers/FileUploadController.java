package org.alexcoders.imageGalleryUploader.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.alexcoders.imageGalleryUploader.dtos.UploadForm;
import org.alexcoders.imageGalleryUploader.models.ImageDescription;
import org.alexcoders.imageGalleryUploader.services.ImageDescriptionService;
import org.alexcoders.imageGalleryUploader.utils.FileNameUtils;
import org.apache.commons.imaging.ImageFormats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.gridfs.GridFSDBFile;

@Controller
public class FileUploadController {

	private static final String UPLOAD_VIEW = "upload";
	private static final String RESULTS_VIEW = "results";
	
	@Autowired
	ImageDescriptionService imageDescriptionService;
	
	@Autowired
	FileNameUtils fileNameUtils;

	@ModelAttribute("supportedExtensions")
	public String getSupportedExtensions() {
		StringBuilder builder = new StringBuilder();
		List<String> supportedExtensions = fileNameUtils.getSupportedExtensions();
		for (int i = 1; i < supportedExtensions.size(); i++) {
			builder.append(".").append(supportedExtensions.get(i).toLowerCase());
			if (i != supportedExtensions.size() - 1) {
				builder.append(",");
			}
		}
		return builder.toString();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String provideUploadInfo(@ModelAttribute("uploadForm") UploadForm uploadForm, ModelMap modelMap) {
		modelMap.addAttribute("message", "test");
		if (uploadForm == null || uploadForm.getFileDescriptions() == null) {
			uploadForm = new UploadForm();
			List<FileDescription> fileDescriptions = new ArrayList<FileDescription>();
			FileDescription fileDescription = new FileDescription();
			fileDescriptions.add(fileDescription);
			uploadForm.setFileDescriptions(fileDescriptions);
		}
		modelMap.addAttribute("uploadForm", uploadForm);
		return UPLOAD_VIEW;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@ModelAttribute("uploadForm") UploadForm uploadForm, ModelMap modelMap,
			BindingResult bindingResult) {
		List<FileDescription> fileDescriptions = uploadForm.getFileDescriptions();
		
		if (uploadForm.isAddAnotherImage()) {
			fileDescriptions.add(new FileDescription());
			uploadForm.setAddAnotherImage(false);
			modelMap.addAttribute("uploadForm", uploadForm);
			return UPLOAD_VIEW;
		}

		imageDescriptionService.save(fileDescriptions);
		List<String> results = getResults(fileDescriptions);
		
		modelMap.addAttribute("results", results  );
		return RESULTS_VIEW;
	}

	private List<String> getResults(List<FileDescription> fileDescriptions) {
		List<String> results = new ArrayList<String>();
		Map<ImageDescription, GridFSDBFile> images = imageDescriptionService.getImages(fileDescriptions);
		
		for (FileDescription fileDescription : fileDescriptions) {
			
			String fileName = fileNameUtils.getFileName( fileDescription);
			boolean filefound = false;
			for (ImageDescription imageDescription : images.keySet()) {
				GridFSDBFile gridFSDBFile = images.get(imageDescription);
				if (gridFSDBFile.getFilename().equals(fileName)) {
					filefound = true;
					break;
				}
			}
			if (filefound) {
				results.add("You successfully uploaded image" + fileName + "!");
			} else {
				results.add("You failed to upload " + fileName );
			}
		}
		return results;
	}

	@RequestMapping(value = "/" + RESULTS_VIEW, method = RequestMethod.GET)
	public String listImages(ModelMap modelMap) {

		return RESULTS_VIEW;
	}

}