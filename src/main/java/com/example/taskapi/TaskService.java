package com.example.taskapi;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    public List<Task> findAll(){
        return new ArrayList<>(store.values());//why .values? why need this if have map
    }
    
    public Optional<Task> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public Task create(Task task){
        long id = idSeq.getAndIncrement(); //why long here not Long and why use long not int?
        task.setId(id);
        store.put(id, task);
        return task;//what about title
    }

    public Optional<Task> update(Long id, Task update){
        Task existing = store.get(id);
        if (existing==null){
            return Optional.empty();//safer way to handle null pointer exception
        }
        existing.setTitle(update.getTitle());
        existing.setDescription(update.getDescription());
        existing.setDone(update.getDone());
        return Optional.of(existing);
    }

    public boolean delete(Long id){
        return store.remove(id)!=null;//returns true if it existed and was deleted, false if it didn't exist in the 1st place
    }

}
