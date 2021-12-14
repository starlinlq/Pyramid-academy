package com.crave.crave.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException {
    private String message;
    private String details;
    private String nextActions;

    public CustomException(String message){
        this.message = message;
    }
}
