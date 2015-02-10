package org.alexcoders.imageGalleryUploader.models;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Entity
public class ImageDescription {

	@Id
	private String id;
	
	private String name;
	private String altTag;
	private String caption;
	private boolean defaultName;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
