package com.usdly.app.secuirty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usdly.app.model.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		log.info("Responding with unauthorized error. Message - {}", exception.getMessage());

//        log.warn("Handle unsuccessful authentication, ip: [{}]", ServletUtil.getClientIP(request));
//        log.error("Authentication failure: [{}], status: [{}], data: [{}]", exception.getMessage(), exception.getStatus(), exception.getErrorData());

		BaseResponse<Object> errorDetail = new BaseResponse<>();

		errorDetail.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		errorDetail.setMessage(exception.getMessage());
		errorDetail.setData(exception.getMessage());

//        if (!productionEnv) {
//            errorDetail.setDevMessage(ExceptionUtils.getStackTrace(exception));
//        }

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(objectMapper.writeValueAsString(errorDetail));

//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,objectMapper.writeValueAsString(errorDetail));
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
