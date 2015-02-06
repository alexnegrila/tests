package org.alexcoders.groupProvider.dtos;

import java.io.Serializable;

public class GroupProviderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private GroupDTO group;

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
