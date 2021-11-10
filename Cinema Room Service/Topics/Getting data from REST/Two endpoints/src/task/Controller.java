package task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Controller {
    List<Student> list = List.of(new Student("Starlin", 84), new Student("Tom", 99), new Student("Alicia", 55));

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getSingleStudent(@PathVariable int id){
        Student studnt = list.stream().filter(student -> student.getId() == id).collect(Collectors.toList()).get(0);
        if(studnt != null){
            return new ResponseEntity<>(studnt, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.OK);


    }
}
