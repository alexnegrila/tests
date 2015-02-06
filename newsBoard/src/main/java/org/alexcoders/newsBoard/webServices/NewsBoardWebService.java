package org.alexcoders.newsBoard.webServices;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.alexcoders.newsBoard.services.NewsBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@RestController
public class NewsBoardWebService {

	@Autowired
	NewsBoardService newsBoardService;

	@RequestMapping(value = "/registerVotes")
	public String registerVotes(@RequestParam(value = "likedNews") List<String> likedNews, HttpServletRequest request) {
		try {
			String filepath = request.getServletContext().getRealPath("/votes.xml");
			newsBoardService.registerVotes(likedNews, filepath);
		} catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String string : likedNews) {
			System.out.println(string);
		}
		return likedNews.toString();
	}

}
