package com.group2.kgrill.service;

import com.group2.kgrill.dto.UserProfileDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<Object> getUserInformation(HttpServletRequest request);

    UserProfileDto updateUserInformation(UUID id, UserProfileDto userProfileDto);

    void updateUserProfilePicture(UUID id, String profilePictureUrl);
}
