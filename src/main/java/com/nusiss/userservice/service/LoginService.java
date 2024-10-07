package com.nusiss.userservice.service;

import com.nusiss.userservice.entity.User;

import java.util.List;

public interface LoginService {

    public String login(String username, String password);

    public boolean validateToken(String token);

}