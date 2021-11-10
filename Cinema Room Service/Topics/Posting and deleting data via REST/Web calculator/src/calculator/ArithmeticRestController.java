package calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class ArithmeticRestController {

    @GetMapping("/{operation}")
    public Object calculate(@PathVariable String operation, @RequestParam int a, @RequestParam int b) {

        switch (operation){
            case "add": return a + b;
            case "subtract": return a - b;
            case "mult": return a * b;
            default: {
                return "Unknown operation";
            }
        }
    }

}