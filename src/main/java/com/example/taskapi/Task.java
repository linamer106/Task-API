package com.example.taskapi;

public class Task {
    private Long id;
    private String description;
    private String title;
    private boolean done;

    public Task(){} //want to try without cause tech shouldn't need it

    public Task(Long id, String description, String title, boolean done){
        this.id=id;
        this.description=description;
        this.title=title;
        this.done=done;
    }

    // Getters:

    public Long getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle(){
        return title;
    }

    public boolean getDone(){
        return done;
    }

    // Setters:

    public void setId(Long id){
        this.id=id;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setDone(boolean done){
        this.done=done;
    }

}
