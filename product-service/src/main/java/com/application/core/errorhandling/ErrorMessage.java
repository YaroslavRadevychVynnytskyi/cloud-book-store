package com.application.core.errorhandling;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
}
