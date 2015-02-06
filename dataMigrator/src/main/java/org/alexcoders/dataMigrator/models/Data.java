package org.alexcoders.dataMigrator.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = Data.TABLE_NAME)
public class Data implements Serializable {
	
	private static final String DELIMITER = ",";

	private static final String SEPARATOR = ":";

	public static final String TABLE_NAME = "Data";

	public static final String ID = "id";
	public static final String ARTICLEID = "articleId";
	public static final String ATTRIBUTE = "attribute";
	public static final String VALUE = "value";
	public static final String LANGUAGE = "language";
	public static final String TYPE = "type";
	
	@Id
	@NotNull
	@Column(name = ID)
	private Integer id;
	
	@Column(name = ARTICLEID)
	private Integer articleId;
	
	@Size(max=20)
	@Column(name = ATTRIBUTE)
	private String attribute;
	
	@Size(max=20000)
	@Column(name = VALUE)
	private String value;
	
	@Column(name = LANGUAGE)
	private Short language;
	
	@Column(name = TYPE)
	private Short type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public Short getLanguage() {
		return language;
	}
	public void setLanguage(Short language) {
		this.language = language;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(ID).append(SEPARATOR).append(this.getId()).append(DELIMITER);
		builder.append(ARTICLEID).append(SEPARATOR).append(this.getArticleId()).append(DELIMITER);
		builder.append(ATTRIBUTE).append(SEPARATOR).append(this.getAttribute()).append(DELIMITER);
		builder.append(VALUE).append(SEPARATOR).append(this.getValue()).append(DELIMITER);
		builder.append(LANGUAGE).append(SEPARATOR).append(this.getLanguage()).append(DELIMITER);
		builder.append(TYPE).append(SEPARATOR).append(this.getType());
		
		return builder.toString();
	}
	
}
