package org.alexcoders.groupProvider.models;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "service_call_table")
public class ServiceCall implements Serializable {
	private static final long serialVersionUID = -68409176025762791L;

	@Id
	@Column(name = "SERVICE_CALL_ID")
	private String id;

	private String name;

//	@RestResource(exported = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SERVICE_PARENT_ID")
	private Service serviceParent;

	@ElementCollection
	@MapKeyColumn(name = "name")
	@Column(name = "value")
	@CollectionTable(name = "service_call_importCommandParameters", joinColumns = @JoinColumn(name = "temp_id"))
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

	public Service getServiceParent() {
		return serviceParent;
	}

	public void setServiceParent(Service serviceParent) {
		this.serviceParent = serviceParent;
	}

	public Map<String, String> getImportCommandParameters() {
		return importCommandParameters;
	}

	public void setImportCommandParameters(Map<String, String> importCommandParameters) {
		this.importCommandParameters = importCommandParameters;
	}

}
