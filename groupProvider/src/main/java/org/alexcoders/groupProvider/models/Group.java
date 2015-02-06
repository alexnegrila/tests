package org.alexcoders.groupProvider.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "group_table")
public class Group implements Serializable {
	private static final long serialVersionUID = -6526031621576557111L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GROUP_ID")
	private Long id;

	//if @RestResource(exported = false) is activated on the JPA models to enable RestRepository creation then RestRepository read is not possible 
	
	//https://jira.spring.io/browse/DATAREST-396
	//http://stackoverflow.com/questions/24569399/how-to-post-nested-entities-with-spring-data-rest
	
//	@RestResource(exported = false)
	@OneToMany(mappedBy = "groupParent", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Service> services;

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
