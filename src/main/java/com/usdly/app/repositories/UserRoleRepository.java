package com.usdly.app.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.usdly.app.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
	List<UserRole> findAllByUserId(long userId);

	List<UserRole> findAllByUserIdIn(Collection<Long> userId);

	List<UserRole> findAllByRoleId(long roleId);

	int deleteByUserId(long userId);
}
