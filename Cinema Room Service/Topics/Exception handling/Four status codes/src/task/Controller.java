package task;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
public class Controller {


    @GetMapping("/test/{status}")
    public String status(@PathVariable int status){
        switch (status){
            case 200:return "200 ok";
            case 300: throw new ResponseStatusException(HttpStatus.MULTIPLE_CHOICES, "300 Multiple Choices");
            case 400: throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "400 Bad Request");
            case 500: throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "500 Internal Server Error");
        }
        return "";
    }
}
