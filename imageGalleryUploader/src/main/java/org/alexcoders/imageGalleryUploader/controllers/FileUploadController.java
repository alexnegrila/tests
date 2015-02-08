package org.alexcoders.imageGalleryUploader.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.alexcoders.imageGalleryUploader.dtos.UploadForm;
import org.alexcoders.imageGalleryUploader.utils.FileNameUtils;
import org.apache.commons.imaging.ImageFormats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private static final String UPLOAD_VIEW = "upload";
	private static final String RESULTS_VIEW = "results";

	@ModelAttribute("supportedExtensions")
	public String getSupportedExtensions() {
		StringBuilder builder = new StringBuilder(".jpg,");
		for (int i = 1; i < ImageFormats.values().length; i++) {
			ImageFormats imageFormat = ImageFormats.values()[i];
			builder.append(".").append(imageFormat.getExtension().toLowerCase());
			if (i != ImageFormats.values().length - 1) {
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
		// return "You can upload a file by posting to this same URL.";
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

		List<String> results = new ArrayList<String>();
		for (FileDescription fileDescription : fileDescriptions) {
			MultipartFile file = fileDescription.getFile();
			String originalFilename = file.getOriginalFilename();
			String name = FileNameUtils.getFileName(originalFilename, fileDescription);
			if (!file.isEmpty()) {
				if (!FileNameUtils.isExtensionSupported(originalFilename)) {
					results.add("Extension is not supported for file " + originalFilename + "!");
				} else {

					try {
						byte[] bytes = file.getBytes();
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
						stream.write(bytes);
						stream.close();
						results.add("You successfully uploaded " + originalFilename + " under name " + name + "!");
					} catch (Exception e) {
						results.add("You failed to upload " + originalFilename + " => " + e.getMessage());
					}
				}
			} else {
				results.add("You failed to upload " + originalFilename + " because the file was empty.");
			}
		}
		modelMap.addAttribute("results", results);
		return RESULTS_VIEW;
	}

	@RequestMapping(value = "/" + RESULTS_VIEW, method = RequestMethod.GET)
	public String listImages(ModelMap modelMap) {

		return RESULTS_VIEW;
	}

}