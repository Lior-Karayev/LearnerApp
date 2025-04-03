package com.learnerapp.model;

public class Topic {
    private int id;
    private int subjectId;
    private String name;
    private String status;

    public Topic(int topicId, int subjectId, String name, String status) {
        this.id = topicId;
        this.subjectId = subjectId;
        this.name = name;
        this.status = status;
    }   

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public int getSubjectId() {
        return subjectId;
    }
    
}
