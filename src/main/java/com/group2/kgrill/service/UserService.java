package com.group2.kgrill.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> getUserInformation(HttpServletRequest request);
}
