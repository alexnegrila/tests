package org.alexcoders.imageGalleryUploader.utils;

import java.util.ArrayList;
import java.util.List;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.apache.commons.imaging.ImageFormats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileNameUtils {

	@Autowired
	Config config;

	public String getFileName(FileDescription fileDescription) {
		MultipartFile file = fileDescription.getFile();
		String originalFilename = file == null ? "" : file.getOriginalFilename();
		originalFilename = StringUtils.isEmpty(originalFilename) ? file.getName() : originalFilename;
		String name = null;
		if (fileDescription.isDefaultName()) {
			name = StringUtils.isEmpty(fileDescription.getAltTag()) ? fileDescription.getCaption() : fileDescription
					.getAltTag();
		}
		name = StringUtils.isEmpty(name) ? fileDescription.getName() : name;
		name = StringUtils.isEmpty(name) ? originalFilename : name;

		String originalExtension = getFileExtension(originalFilename);
		String newExtension = getFileExtension(name);
		return originalExtension.equals(newExtension) ? name : name + originalExtension;
	}

	public String getFileExtension(String filename) {
		if (filename.contains(".")) {
			return filename.substring(filename.lastIndexOf("."));
		}
		return "";

	}

	public List<String> getSupportedExtensions() {
		List<String> extensionList = new ArrayList<String>();
		for (int i = 0; i < ImageFormats.values().length; i++) {
			ImageFormats imageFormat = ImageFormats.values()[i];
			extensionList.add(imageFormat.getExtension());
		}
		List<String> additionalExtensions = config.getAdditionalExtensions();
		extensionList.addAll(additionalExtensions);
		return extensionList;
	}

	public boolean isExtensionSupported(String originalFilename) {
		String originalExtension = getFileExtension(originalFilename);
		if ((".jpg").equalsIgnoreCase(originalExtension)) {
			return true;
		}
		for (int i = 0; i < ImageFormats.values().length; i++) {
			ImageFormats imageFormat = ImageFormats.values()[i];
			if (("." + imageFormat.getExtension()).equalsIgnoreCase(originalExtension)) {
				return true;
			}
		}
		return false;
	}
}
