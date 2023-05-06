package com.ab.service;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entity.LinkShortenerEntity;
import com.ab.repository.ILinkShortenerRepository;

@Service
public class LinkShortenerServiceImpl implements ILinkShortenerService {

	@Autowired
	private ILinkShortenerRepository linkRepo;
	
	@Autowired
	private HttpSession ses;
	
	@Override
	public String saveDetails(LinkShortenerEntity entity) {
		if(linkRepo.getShortUrl(entity.getOriginalUrl())==null) {
		String rmd=RandomStringUtils.random (6, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789");
		entity.setShortUrl(rmd);
		linkRepo.save(entity);
		ses.setAttribute("rmd", rmd);
		ses.setMaxInactiveInterval(20);
		return rmd;
		}
		else {
			return linkRepo.getShortUrl(entity.getOriginalUrl());
		}
	}

	@Override
	public String originalUrl(String shortUrl) {
		String org=null;
		if(ses.getAttribute("rmd")!=null) {
		org=linkRepo.getOriginalUrl(shortUrl);
		}
		return org;
	}

}
