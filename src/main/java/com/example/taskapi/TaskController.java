package com.example.taskapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins="http://localhost:3000") //what if i chnage link or from http to https, why is backen port specifically 8080

public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;//why need to inject this? does this mean cant use normal constructor anymore?
    }
    
    @GetMapping
    public List<Task> all(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
     public ResponseEntity<Task> create(@RequestBody Task task){
        Task created = service.create(task);
        return ResponseEntity.created(URI.create("api/tasks/"+created.getId())).body(created);
     }

    @PutMapping("/{id}")
     public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
        return service.update(id, task).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
     }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        if(service.delete(id)){
            return ResponseEntity.noContent().build();//??
        }
        else{
            return ResponseEntity.notFound().build();//difference how?
        }
     }


}
