package com.nusiss.userservice.controller;

import com.nusiss.userservice.config.ApiResponse;
import com.nusiss.userservice.entity.User;
import com.nusiss.userservice.service.JwtTokenService;
import com.nusiss.userservice.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> login(HttpServletResponse response, @RequestBody Map<String, String> requestParams) {
        String username = requestParams.get("username");
        String password = requestParams.get("password");

        String token = loginService.login(username, password);
        //jwtTokenService.addTokenToCookie(response, token);

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "Login successfully", token));
    }

    @RequestMapping(value = "/validateToken", method = RequestMethod.POST)
    public boolean validateToken(@RequestBody Map<String, String> requestParams) {
        String token = requestParams.get("token");
        boolean isValidated = loginService.validateToken(token);

        return isValidated;
    }
}
