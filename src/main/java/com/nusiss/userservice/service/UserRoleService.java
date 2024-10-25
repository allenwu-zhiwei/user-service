package com.nusiss.userservice.service;

import com.nusiss.userservice.entity.Role;

import java.util.Optional;

public interface UserRoleService
{
    public Role getRoleByUserId(Long userId);

    public void addRoleToUser(Long userId, Long roleId);

    public void removeRoleFromUser(Long userId);

    public void updateUserRole(Long userId, Long newRoleId);
}
