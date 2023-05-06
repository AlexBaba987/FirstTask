package com.ab.service;

import com.ab.entity.LinkShortenerEntity;

public interface ILinkShortenerService {
	public String saveDetails(LinkShortenerEntity entity);
	public String originalUrl(String url);
}
