package org.alexcoders.imageGalleryUploader.utils;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.apache.commons.imaging.ImageFormats;
import org.springframework.util.StringUtils;

public class FileNameUtils {

	public static String getFileName(String originalFilename, FileDescription fileDescription) {
		String name = null;
		if (fileDescription.isDefaultName()) {
			name = StringUtils.isEmpty(fileDescription.getAltTag()) ? fileDescription.getAltTag() : fileDescription
					.getCaption();
		}
		name = StringUtils.isEmpty(fileDescription.getName()) ? originalFilename : fileDescription.getName();

		String originalExtension = getFileExtension(originalFilename);
		String newExtension = getFileExtension(name);
		return originalExtension.equals(newExtension) ? name : name + originalExtension;
	}

	public static String getFileExtension(String filename) {
		if (filename.contains(".")) {
			return filename.substring(filename.lastIndexOf("."));
		}
		return "";

	}
	
	public static boolean isExtensionSupported(String originalFilename) {
		String originalExtension = FileNameUtils.getFileExtension(originalFilename);
		
		for (int i = 0; i < ImageFormats.values().length; i++) {
			ImageFormats imageFormat = ImageFormats.values()[i];
			if (("."+imageFormat.getExtension()).equalsIgnoreCase(originalExtension)) {
				return true;
			}
		}
		return false;
	}
}
