package org.alexcoders.groupProvider.dtos;

import java.io.Serializable;
import java.util.List;

public class GroupDTO implements Serializable {
	private static final long serialVersionUID = -6526031621576557111L;

	private Long id;
	
	private List<ServiceDTO> services;

	public List<ServiceDTO> getServices() {
		return services;
	}

	public void setServices(List<ServiceDTO> services) {
		this.services = services;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
