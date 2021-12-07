package account.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The password length must be at leat 12 chars!")
public class PasswordLengthException extends RuntimeException{
}
