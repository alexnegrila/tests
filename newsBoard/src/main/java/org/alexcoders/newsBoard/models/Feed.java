package org.alexcoders.newsBoard.models;
import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed {

  final private String title;
  final private String link;
  final private String description;
  final private String language;
  final private String copyright;
  final private String lastBuildDate;

  final List<FeedMessage> messages = new ArrayList<FeedMessage>();

  public Feed(String title, String link, String description, String language,
      String copyright, String lastBuildDate) {
    this.title = title;
    this.link = link;
    this.description = description;
    this.language = language;
    this.copyright = copyright;
    this.lastBuildDate = lastBuildDate;
  }

  public List<FeedMessage> getMessages() {
    return messages;
  }

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getDescription() {
    return description;
  }

  public String getLanguage() {
    return language;
  }

  public String getCopyright() {
    return copyright;
  }

  public String getLastBuildDate() {
	return lastBuildDate;
}


@Override
  public String toString() {
    return "Feed [copyright=" + copyright + ", description=" + description
        + ", language=" + language + ", link=" + link + ", pubDate="
        + lastBuildDate + ", title=" + title + "]";
  }

} 