package com.islomar.yaus.web;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.islomar.yaus.core.model.URLShortenerService;


@RestController
public class UrlShortenerRestController {

  private final static Logger LOG = LoggerFactory.getLogger(UrlShortenerRestController.class);

  private final URLShortenerService urlShortenerService;

  @Autowired
  public UrlShortenerRestController(URLShortenerService urlShortenerService) {

    this.urlShortenerService = urlShortenerService;
  }
  
  private String getIpAddr(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
					//Get the IP configured by the machine according to the network card
					InetAddress inet=null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress= inet.getHostAddress();
				}
			}
			//For the case of passing through multiple proxies, the first IP is the real IP of the client, and multiple IPs are divided according to ','
			if(ipAddress!=null && ipAddress.length()>15){
				if(ipAddress.indexOf(",")>0){
					ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
				}
			}
			return ipAddress; 
	}

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "URL Shortener up and running!";
  }

  @RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
  void redirectToFullUrl(@PathVariable String shortUrlId, HttpServletRequest request,HttpServletResponse response) throws IOException {

    LOG.debug("Received request to redirect to '{}'", shortUrlId);
    LOG.info("ip:{},url-{}",getIpAddr(request),request.getRequestURL().toString());
    Optional<URL> shortenedURLFound = urlShortenerService.findURLById(shortUrlId);
    if (shortenedURLFound.isPresent()) {
      LOG.info("Redirecting to {}...", shortenedURLFound.get());
      response.sendRedirect(shortenedURLFound.get().toString());
    } else {
      LOG.error("The id '{}' was not found", shortUrlId);
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
  ResponseEntity<String> createShortUrl(@RequestBody String uriStringToBeShortened, HttpServletRequest request) {

    LOG.debug("Received request to shorten the URL '{}'", uriStringToBeShortened);
    try {
      String shortenedUrlId = urlShortenerService.shorten(uriStringToBeShortened);
      String shortenedUrl = request.getRequestURL() + shortenedUrlId;
      return new ResponseEntity<String>(shortenedUrl, HttpStatus.CREATED);
    } catch (IllegalArgumentException | MalformedURLException ex) {
      LOG.error("Error when trying to create a shortened URL for {}: {}", uriStringToBeShortened, ex.getMessage());
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      LOG.error("Ooopsss... something unexpected went wrong: " + ex.getMessage());
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
