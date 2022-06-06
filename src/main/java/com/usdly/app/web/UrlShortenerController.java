package com.usdly.app.web;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usdly.app.domain.UsdlyUrl;
import com.usdly.app.dto.UsdlyUrlRq;
import com.usdly.app.dto.UsdlyUrlRs;
import com.usdly.app.services.URLShortenerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/shortner")
public class UrlShortenerController {

	private final static Logger LOG = LoggerFactory.getLogger(UrlShortenerController.class);

	@Autowired
	private URLShortenerService urlShortenerService;

//	private String getIpAddr(HttpServletRequest request) {
//		String ipAddress = request.getHeader("x-forwarded-for");
//		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getRemoteAddr();
//			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
//				// Get the IP configured by the machine according to the network card
//				InetAddress inet = null;
//				try {
//					inet = InetAddress.getLocalHost();
//				} catch (UnknownHostException e) {
//					e.printStackTrace();
//				}
//				ipAddress = inet.getHostAddress();
//			}
//		}
//		// For the case of passing through multiple proxies, the first IP is the real IP
//		// of the client, and multiple IPs are divided according to ','
//		if (ipAddress != null && ipAddress.length() > 15) {
//			if (ipAddress.indexOf(",") > 0) {
//				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//			}
//		}
//		return ipAddress;
//	}

//	@RequestMapping("/")
//	@ResponseBody
//	String home() {
//		return "URL Shortener up and running!";
//	}
//
//	@RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
//	void redirectToFullUrl(@PathVariable String shortUrlId, HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//
//		LOG.debug("Received request to redirect to '{}'", shortUrlId);
//		LOG.info("ip:{},url-{}", getIpAddr(request), request.getRequestURL().toString());
//
//		String binfile = "IP2LOCATION-LITE-DB11.BIN";
//
//		IP2Location loc = new IP2Location();
//		loc.Open(binfile, true);
//
//		IPResult rec = loc.IPQuery(getIpAddr(request));
//
//		if ("OK".equals(rec.getStatus())) {
//			LOG.info("REC:{}", rec.getCountryLong());
//			System.out.println(rec);
//		}
//
//		Optional<UsdlyUrl> shortenedURLFound = urlShortenerService.findURLById(shortUrlId);
//		if (shortenedURLFound.isPresent()) {
//			LOG.info("Redirecting to {}...", shortenedURLFound.get());
//			response.sendRedirect(shortenedURLFound.get().toString());
//		} else {
//			LOG.error("The id '{}' was not found", shortUrlId);
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//		}
//	}

	@PostMapping(name = "/create")
	public UsdlyUrlRs createShortUrl(@RequestBody UsdlyUrlRq rq, HttpServletRequest request) {
		UsdlyUrlRs rs = new UsdlyUrlRs();
		LOG.debug("Received request to shorten the URL '{}'", rq);
		try {
			UsdlyUrl usdlyUrl = urlShortenerService.shorten(rq.getUrl());
			String shortenedUrl = request.getRequestURL() + usdlyUrl.getLinkSlug();

			rs.setShortedUrl(shortenedUrl);
			return rs;
		} catch (IllegalArgumentException | MalformedURLException ex) {
			LOG.error("Error when trying to create a shortened URL for {}: {}", rq.getUrl(), ex.getMessage());
			return rs;
		} catch (Exception ex) {
			LOG.error("Ooopsss... something unexpected went wrong: " + ex.getMessage());
			return rs;
		}

	}

}
