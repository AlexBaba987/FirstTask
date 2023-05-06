package com.ab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ab.entity.LinkShortenerEntity;

public interface ILinkShortenerRepository extends JpaRepository<LinkShortenerEntity, Integer> {
	
	@Query("select originalUrl from com.ab.entity.LinkShortenerEntity t where t.shortUrl=:url")
	public String getOriginalUrl(@Param("url") String url);
	
	@Query("select shortUrl from com.ab.entity.LinkShortenerEntity t where t.originalUrl=:url")
	public String getShortUrl(@Param("url") String url);
}
