package task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class Controller {
    public HashMap<String, List<Color>> list = new HashMap<>();

    @GetMapping("/colors")
    public ResponseEntity<HashMap<String, List<Color>>> getColor(){
        HashMap<String, Object> obj1 = new HashMap<>();
        obj1.put("rgba", List.of(0, 0 , 0, 1));
        obj1.put("hex", "#000");

        HashMap<String, Object> obj2 = new HashMap<>();
        obj2.put("rgba", List.of(255, 255, 255, 1));
        obj2.put("hex", "#FFF");

        List<Color> colors = List.of(new Color("black", "hue", "primary", obj1), new Color("white", "value", "primary", obj2));
        list.put("colors", colors);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }
}