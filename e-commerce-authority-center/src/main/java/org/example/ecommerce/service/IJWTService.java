package org.example.ecommerce.service;

import org.example.ecommerce.vo.UsernameAndPassword;

public interface IJWTService {
    String generateToken(String username, String password) throws Exception;
    String generateToken(String username, String password, int expire) throws Exception;
    String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception;
}
