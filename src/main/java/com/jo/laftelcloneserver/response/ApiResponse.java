package com.jo.laftelcloneserver.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private HttpStatus status;
    private String path;
    private ErrorResponse error;
    private T data;
}