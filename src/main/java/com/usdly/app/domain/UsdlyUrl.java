package com.usdly.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.usdly.app.domain.audit.UserDateAudit;

import lombok.Data;

@Data
@Entity
@Table(name = "usdly_url", uniqueConstraints = { @UniqueConstraint(columnNames = { "ORIGINAL_URL" }),
		@UniqueConstraint(columnNames = { "LINK_SLUG" }) })
public class UsdlyUrl extends UserDateAudit{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "ORIGINAL_URL", unique = true, nullable = false, length = 3000)
	private String originalUrl;
	
	@Column(name = "SHORT_URL", unique = true, nullable = false, length = 300)
	private String shortUrl;
	
	@NotBlank
	@Column(name = "LINK_SLUG", unique = true, nullable = false, length = 10)
	private String linkSlug;
	
	private String linkTitle;

	private String domain;
	
	private Long clickLimit;
	
}
