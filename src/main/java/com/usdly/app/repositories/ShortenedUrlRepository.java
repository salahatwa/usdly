package com.usdly.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usdly.app.domain.UsdlyUrl;

public interface ShortenedUrlRepository extends JpaRepository<UsdlyUrl,String>
{

	Optional<UsdlyUrl> findURLById(String shortenedId);
}
