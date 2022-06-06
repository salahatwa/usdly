package com.usdly.app.services;

import java.net.MalformedURLException;
import java.util.Optional;

import com.usdly.app.domain.UsdlyUrl;

public interface URLShortenerService {

	public UsdlyUrl shorten(String urlStringToBeShortened) throws MalformedURLException;

	public Optional<UsdlyUrl> findURLById(String shortUrlId);
}
