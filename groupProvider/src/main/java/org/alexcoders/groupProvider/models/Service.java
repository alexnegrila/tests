package org.alexcoders.groupProvider.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "service_table")
public class Service implements Serializable {
	private static final long serialVersionUID = 6832924458503622132L;

	@Id
	@Column(name = "SERVICE_ID")
	private String id;

	private String name;
	private String importCommand;

//	@RestResource(exported = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GROUP_PARENT_ID")
	private Group groupParent;

	@RestResource(exported = false)
	@OneToMany(mappedBy = "serviceParent", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ServiceCall> serviceCalls;

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

	public Group getGroupParent() {
		return groupParent;
	}

	public void setGroupParent(Group groupParent) {
		this.groupParent = groupParent;
	}

	public List<ServiceCall> getServiceCalls() {
		return serviceCalls;
	}

	public void setServiceCalls(List<ServiceCall> serviceCalls) {
		this.serviceCalls = serviceCalls;
	}
}
