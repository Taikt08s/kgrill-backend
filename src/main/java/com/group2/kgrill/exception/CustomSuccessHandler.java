package com.group2.kgrill.exception;

import com.group2.kgrill.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomSuccessHandler {
    public static ResponseEntity<Object> responseBuilder(HttpStatus httpStatus, String message, Object responseObject) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("httpStatus", httpStatus.value());
        response.put("timestamp", DateUtil.formatTimestamp(new Date()));
        response.put("message", message);
        response.put("data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }
}

