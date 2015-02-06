package org.alexcoders.newsBoard.controllers;

import org.alexcoders.newsBoard.models.Feed;
import org.alexcoders.newsBoard.services.NewsBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NewsBoardController {
	
	@Autowired
	NewsBoardService newsBoardService; 

	@RequestMapping(value="/newsBoard" ,method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		
		Feed feed = newsBoardService.getFeed();
		model.addAttribute("feed", feed);
		
		return "newsBoard";
	}
}
