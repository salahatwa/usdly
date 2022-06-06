package com.usdly.app.services.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usdly.app.domain.UsdlyUrl;
import com.usdly.app.repositories.ShortenedUrlRepository;
import com.usdly.app.services.URLShortenerService;
import com.usdly.app.utils.IdGenerator;

@Service
public final class URLShortenerServiceImpl implements URLShortenerService {

	@Autowired
	private ShortenedUrlRepository shortenedUrlRepository;
	@Autowired
	private IdGenerator idGenerator;

	public UsdlyUrl shorten(String urlStringToBeShortened) throws MalformedURLException {

		
		
		UsdlyUrl urlToBeShortened =new UsdlyUrl();
		
		String shortenedUrlId = this.idGenerator.generateIdFrom(URI.create(urlStringToBeShortened).toURL().toString().toString());
		
		urlToBeShortened.setOriginalUrl(urlStringToBeShortened);
		urlToBeShortened.setShortUrl(URI.create(urlStringToBeShortened).toURL().toString());
		
		urlToBeShortened.setLinkSlug(shortenedUrlId);

		urlToBeShortened=shortenedUrlRepository.save(urlToBeShortened);
		


		return urlToBeShortened;
	}

	public Optional<UsdlyUrl> findURLById(String shortUrlId) {
		return shortenedUrlRepository.findURLById(shortUrlId);
	}
}
