package com.group2.kgrill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for user sign in")
public class AuthenticationResponse {
    @Schema(description = "Access Token", example = "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjp7InJvbGVJZCI6MSwicm9sZU5hbWUiOiJVU0VSIiwiY3JlYXRlZERhdGUiOjE3MTY5MTQ5MTEwMDAsImxhc3RNb2RpZmllZERhdGUiOjE3MTY5MTQ5MTIwMDB9LCJmdWxsTmFtZSI6IkRhbmcgRGluaCBUYWkiLCJzdWIiOiJzdHllbWF0aWNAZ21haWwuY29tIiwiaWF0IjoxNzE2OTU2NDAzLCJleHAiOjE3MTY5NTgyMDMsImF1dGhvcml0aWVzIjpbIlVTRVIiXX0.kbA1vVt5AyocVTX1YCv_oBVuuPdiiiKYEVd-9NzZiPyNS48YTOGGjdIzTotQUkv3wEzGWACjtxKx1tSWOIOHKA")
    @JsonProperty("accessToken")
    private String accessToken;

    @Schema(description = "Refresh Token", example = "")
    @JsonProperty("refreshToken")
    private String refreshToken;
}

