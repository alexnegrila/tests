package org.alexcoders.newsBoard.services;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.alexcoders.newsBoard.models.Feed;
import org.xml.sax.SAXException;

public interface NewsBoardService {

	Feed getFeed();

	void registerVotes(List<String> likedNews, String filePath) throws IOException, ParserConfigurationException,
			SAXException, TransformerException;

}
