package org.alexcoders.imageGalleryUploader.dtos;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileDescription implements Serializable{
	private static final long serialVersionUID = -7772093566180660935L;
	private String name;
	private String altTag;
	private String caption;
	private boolean defaultName;
	
	 private MultipartFile file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAltTag() {
		return altTag;
	}

	public void setAltTag(String altTag) {
		this.altTag = altTag;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public boolean isDefaultName() {
		return defaultName;
	}

	public void setDefaultName(boolean defaultName) {
		this.defaultName = defaultName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	 
}
