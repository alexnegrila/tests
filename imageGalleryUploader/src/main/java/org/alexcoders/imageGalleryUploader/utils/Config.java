package org.alexcoders.imageGalleryUploader.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="igu")
public class Config {

    private List<String> additionalExtensions = new ArrayList<String>();

	public List<String> getAdditionalExtensions() {
		return additionalExtensions;
	}

	public void setAdditionalExtensions(List<String> additionalExtensions) {
		this.additionalExtensions = additionalExtensions;
	}

    
}