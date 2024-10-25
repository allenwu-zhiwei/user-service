package com.nusiss.userservice.dao;

import com.nusiss.userservice.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // 根据用户ID查找关联的单个 UserRole
    Optional<UserRole> findByUserId(Long userId);
}