package com.crave.crave.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InvalidCategoryException extends CustomException{

  public InvalidCategoryException(String message){
      super(message);
  }

  public InvalidCategoryException(String message, String details, String nextAction){
      super(message, details, nextAction);
  }
}
