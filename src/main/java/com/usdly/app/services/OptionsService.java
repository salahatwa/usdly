package com.usdly.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.usdly.app.domain.Options;

public interface OptionsService {

	List<Options> findAll();

	void update(Map<String, String> options);

	void initSettings(Resource resource);
}
