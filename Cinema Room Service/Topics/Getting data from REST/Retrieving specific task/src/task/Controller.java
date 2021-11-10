package task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

class Task {
    private final int id;
    private final String name;
    private final String description;

    Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

@RestController
public class Controller {
    List<Task> tasks = List.of(
            new Task(405, "Improve UI", "implement ..."),
            new Task(406, "Color bug", "fix ...")
    );

    Task defaultTask = new Task(0, null, null);

// Add your code below this line and do not change the code above the line.
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getSingleTask(@PathVariable int id){
        List<Task> singleTask = tasks.stream().filter(task -> task.getId() == id).collect(Collectors.toList());

        if(singleTask.size() > 0){
            return new ResponseEntity<>(singleTask.get(0), HttpStatus.OK);
        } else
            return new ResponseEntity<>(defaultTask, HttpStatus.OK);
    }


}
