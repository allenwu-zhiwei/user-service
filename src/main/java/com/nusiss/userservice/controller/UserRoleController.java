package com.nusiss.userservice.controller;

import com.nusiss.userservice.entity.Role;
import com.nusiss.userservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
@RequestMapping("/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    // 获取用户的角色（单个）
    @GetMapping("/user/{userId}")
    public ResponseEntity<Role> getUserRole(@PathVariable Long userId) {
        Role role = userRoleService.getRoleByUserId(userId);
        return ResponseEntity.of(Optional.ofNullable(role));
    }

    // 为用户添加角色
    @PostMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.addRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }

    // 删除用户的角色
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable Long userId) {
        userRoleService.removeRoleFromUser(userId);
        return ResponseEntity.noContent().build();
    }

    // 更新用户的角色
    @PutMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.updateUserRole(userId, roleId);
        return ResponseEntity.ok().build();
    }
}