package com.nusiss.userservice.service;

import com.nusiss.userservice.entity.Role;
import com.nusiss.userservice.entity.UserRole;
import com.nusiss.userservice.dao.RoleRepository;
import com.nusiss.userservice.dao.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    // 根据用户ID查询关联的角色
    public Optional<Role> getRoleByUserId(Long userId) {
        // 获取用户的 UserRole 实体
        Optional<UserRole> userRole = userRoleRepository.findByUserId(userId);

        // 如果存在，则根据 roleId 查找角色
        return userRole.flatMap(ur -> roleRepository.findById(ur.getRoleId()));
    }

    // 为用户添加新角色
    public void addRoleToUser(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleRepository.save(userRole);
    }

    // 删除用户的角色
    public void removeRoleFromUser(Long userId) {
        userRoleRepository.findByUserId(userId).ifPresent(userRoleRepository::delete);
    }

    // 更新用户的角色
    public void updateUserRole(Long userId, Long newRoleId) {
        // 删除旧的角色
        removeRoleFromUser(userId);

        // 添加新的角色
        addRoleToUser(userId, newRoleId);
    }
}