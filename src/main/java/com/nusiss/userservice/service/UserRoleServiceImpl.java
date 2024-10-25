package com.nusiss.userservice.service;

import com.nusiss.userservice.entity.Role;
import com.nusiss.userservice.dao.RoleRepository;
import com.nusiss.userservice.dao.UserRoleRepository;
import com.nusiss.userservice.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByUserId(Long userId) {
        Optional<UserRole> userRole = userRoleRepository.findByIdUserId(userId);
        if (userRole.isPresent()) {
            return roleRepository.findById(userRole.get().getId().getRoleId());
        }
        return Optional.empty();
    }

}