package org.alexcoders.imageGalleryUploader.dtos;

import java.io.Serializable;
import java.util.List;

public class UploadForm implements Serializable{
	private static final long serialVersionUID = -932324856722449890L;

	private List<FileDescription> fileDescriptions;
	
	private boolean addAnotherImage;
	
	public List<FileDescription> getFileDescriptions() {
		return fileDescriptions;
	}

	public void setFileDescriptions(List<FileDescription> fileDescriptions) {
		this.fileDescriptions = fileDescriptions;
	}

	public boolean isAddAnotherImage() {
		return addAnotherImage;
	}

	public void setAddAnotherImage(boolean addAnotherImage) {
		this.addAnotherImage = addAnotherImage;
	}

}
