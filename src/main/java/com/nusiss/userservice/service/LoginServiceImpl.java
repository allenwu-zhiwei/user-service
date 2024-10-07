package com.nusiss.userservice.service;

import com.nusiss.userservice.config.CustomException;
import com.nusiss.userservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public String login(String username, String password) {

        List<User> users = userService.findUserByUsernameAndPassword(username, password);

        //validate username
        if(users == null || users.size() != 1){
            throw new CustomException("Invalid username/password.");
        }

        //get token by username and password
        String token = jwtTokenService.generateToken(username, password);

        return token;
    }

    public boolean validateToken(String token){
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(JwtTokenService.SECRET_KEY) // Set the key used to validate the signature
                .build()
                .parseClaimsJws(token) // Parse the token
                .getBody(); // Get the claims from the token


        // Extract information from the claims
        String username = claims.get("username", String.class);
        String password = claims.get("password", String.class);
        List<User> users = userService.findUserByUsernameAndPassword(username, password);

        if(users!=null && users.size() == 1){
            return true;
        } else {
            return false;
        }
    }

    private String getExpiredDateTime(){
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30); // Add 30 minutes

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expirationDateStr = sdf.format(calendar.getTime());

        return expirationDateStr;
    }
}
