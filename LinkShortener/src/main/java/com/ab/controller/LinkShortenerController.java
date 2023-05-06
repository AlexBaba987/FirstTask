package com.ab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ab.entity.LinkShortenerEntity;
import com.ab.service.ILinkShortenerService;

@Controller
public class LinkShortenerController {
	
	private String rmd=null;
	
	@Autowired
	private ILinkShortenerService linkService;

	@GetMapping("/")
	public String showIndex(@ModelAttribute("url") LinkShortenerEntity entity) {
		return "index";
	}
	
	@PostMapping("/link")
	public String saveUrl(@ModelAttribute("url") LinkShortenerEntity entity) {
		rmd=linkService.saveDetails(entity);
		entity.setShortUrl("http://localhost:4041/LinkShortener/"+rmd);
		return "index";
	}
	
	@GetMapping("/{url}")
	public String accessOriginalUrl(@PathVariable("url") String val) {
			String originalUrl=linkService.originalUrl(val);
		if(originalUrl!=null) {
			return "redirect:"+originalUrl;
		}
		return "redirect:./";
	}
}
