package org.alexcoders.newsBoard.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.alexcoders.newsBoard.models.Feed;
import org.alexcoders.newsBoard.utils.RSSFeedParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class NewsBoardServiceImpl implements NewsBoardService {

	@Override
	public Feed getFeed() {
		RSSFeedParser parser = new RSSFeedParser(
				"http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss.xml");
		Feed feed = parser.readFeed();
		return feed;
	}

	@Override
	public void registerVotes(List<String> likedNews, String filepath) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {

		File votesFile = new File(filepath);

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = null;
		boolean fileExists = votesFile.exists();
		if (!fileExists) {
			// new document
			votesFile.createNewFile();
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("newsBoard");
			doc.appendChild(rootElement);
			Element votes = doc.createElement("votes");
			rootElement.appendChild(votes);

			for (String newsId : likedNews) {
				Element vote = doc.createElement("vote");
				vote.setAttribute("guid", newsId);
				vote.setAttribute("count", "1");
				votes.appendChild(vote);
			}

		} else {
			// update document
			doc = docBuilder.parse(filepath);
			// Get the root element
			Node votes = doc.getElementsByTagName("votes").item(0);
			NodeList voteList = doc.getElementsByTagName("vote");
			for (int i = 0; i < voteList.getLength(); i++) {
				Node vote = voteList.item(i);
				NamedNodeMap attributes = vote.getAttributes();
				Node guidAttr = attributes.getNamedItem("guid");
				String textContent = guidAttr.getTextContent();
				if (textContent != null && likedNews.contains(textContent)) {
					Node countAttr = attributes.getNamedItem("count");
					String oldCount = countAttr.getTextContent();
					int incrementedCount = Integer.valueOf(oldCount) + 1;
					countAttr.setTextContent(String.valueOf(incrementedCount));
					likedNews.remove(textContent);
				}
			}
			for (String newsId : likedNews) {
				Element vote = doc.createElement("vote");
				vote.setAttribute("guid", newsId);
				vote.setAttribute("count", "1");
				votes.appendChild(vote);
			}

		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(votesFile);

		transformer.transform(source, result);

		System.out.println("File saved!");

	}

}
