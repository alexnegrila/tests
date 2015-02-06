package org.alexcoders.newsBoard.servicesTests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import junit.framework.Assert;

import org.alexcoders.newsBoard.models.Feed;
import org.alexcoders.newsBoard.models.FeedMessage;
import org.alexcoders.newsBoard.services.NewsBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/newsBoard-servlet.xml")
public class NewsBoardServiceTest {

	@Autowired
	NewsBoardService newsBoardService;

	@Test
	public void getFeedTest() {
		Feed feed = newsBoardService.getFeed();
		Assert.assertTrue("not good", feed != null && feed.getMessages().size() > 0);
		System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {
			System.out.println(message);
		}
	}
	
	@Test
	public void registerVotesTest() {
		List<String> likedNews = new ArrayList<String>();
		String testNews = "Good News";
		likedNews.add(testNews);
		try {
			newsBoardService.registerVotes(likedNews, "votes.xml");
		} catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String filepath = "/votes.xml";
			File votesFile = new File(filepath);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			boolean fileExists = votesFile.exists();
			Assert.assertTrue("not good", fileExists);
			
			boolean voteRegistered = false;
			NodeList voteList = doc.getElementsByTagName("vote");
			for (int i = 0; i < voteList.getLength(); i++) {
				Node vote = voteList.item(i);
				NamedNodeMap attributes = vote.getAttributes();
				Node guidAttr = attributes.getNamedItem("guid");
				if (guidAttr.getTextContent().equals(testNews)) {
					voteRegistered = true;
				}
			}
			Assert.assertTrue("not good", voteRegistered);
		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
