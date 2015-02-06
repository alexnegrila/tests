package org.alexcoders.groupProvider.dtos;

import java.io.Serializable;
import java.util.List;

public class ServiceDTO implements Serializable {
	private static final long serialVersionUID = 6832924458503622132L;

	private String id;

	private String name;
	private String importCommand;

	private List<ServiceCallDTO> serviceCalls;

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

	public String getImportCommand() {
		return importCommand;
	}

	public void setImportCommand(String importCommand) {
		this.importCommand = importCommand;
	}

	public List<ServiceCallDTO> getServiceCalls() {
		return serviceCalls;
	}

	public void setServiceCalls(List<ServiceCallDTO> serviceCalls) {
		this.serviceCalls = serviceCalls;
	}
}
