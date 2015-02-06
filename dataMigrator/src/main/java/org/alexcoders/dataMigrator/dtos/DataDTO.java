package org.alexcoders.dataMigrator.dtos;


public class DataDTO {
	
	private Integer id;
	private Integer articleId;
	
	private String attribute;
	private String value;
	private Short language;
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
	
	
}
