package org.alexcoders.groupProvider.dtos;

import java.io.Serializable;
import java.util.Map;

public class ServiceCallDTO implements Serializable {
	private static final long serialVersionUID = -68409176025762791L;

	private String id;

	private String name;

	private Map<String, String> importCommandParameters;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getImportCommandParameters() {
		return importCommandParameters;
	}

	public void setImportCommandParameters(Map<String, String> importCommandParameters) {
		this.importCommandParameters = importCommandParameters;
	}

}
