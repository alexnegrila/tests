package org.alexcoders.newsBoard.models;
/*
 * Represents one RSS message
 */
public class FeedMessage {

  private String title;
  private String description;
  private String link;
  private String guid;
  private String pubDate;
  private String thumbnail;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }


  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }
  
  

  @Override
  public String toString() {
    return "FeedMessage [title=" + title + ", description=" + description
        + ", link=" + link + ", guid=" + guid
        + "]";
  }

public String getPubDate() {
	return pubDate;
}

public void setPubDate(String pubDate) {
	this.pubDate = pubDate;
}

public String getThumbnail() {
	return thumbnail;
}

public void setThumbnail(String thumbnail) {
	this.thumbnail = thumbnail;
}

} 