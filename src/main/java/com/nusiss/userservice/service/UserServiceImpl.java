package com.nusiss.userservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.userservice.config.CustomException;
import com.nusiss.userservice.dao.UserRepository;
import com.nusiss.userservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisCrudService redisCrudService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findUserByUsernameAndPassword(String username, String password){

        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User getCurrentUserInfo(String authToken) {
        try{
            Claims claims =  Jwts.parserBuilder()
                    .setSigningKey(JwtTokenService.SECRET_KEY) // Set the key used to validate the signature
                    .build()
                    .parseClaimsJws(authToken) // Parse the token
                    .getBody(); // Get the claims from the token


            // Extract information from the claims
            String username = claims.get("username", String.class);
            if(redisCrudService.exists(username)){
                String userJson = redisCrudService.get(username);
                return objectMapper.readValue(userJson, User.class);
            } else {
                throw new CustomException("Fail to get user info: user didn't login");
            }

        } catch (Exception e){
            logger.info("Fail to get user info: {}", e.getMessage());

            throw new CustomException("Fail to get user info: {}" + e.getMessage());
        }

    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(Integer.valueOf(id));
    }
}
