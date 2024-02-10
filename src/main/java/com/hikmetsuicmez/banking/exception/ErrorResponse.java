package com.hikmetsuicmez.banking.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Getter
@Setter
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private Date timestamp;
}
