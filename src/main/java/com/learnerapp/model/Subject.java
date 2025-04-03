package com.learnerapp.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Subject {
    private final SimpleStringProperty name;
    private final int progress;
    private final int totalTopics;
    private final int completedTopics;
    private final int subjectId;

    public Subject(String name, int progress, int totalTopics, int completedTopics, int subjectId) {
        this.name = new SimpleStringProperty(name);
        this.progress = progress;
        this.totalTopics = totalTopics;
        this.completedTopics = completedTopics;
        this.subjectId = subjectId;
    }

    public String getName() {
        return name.get();
    }

    public int getCompletedTopics() {
        return completedTopics;
    }

    public int getProgress() {
        return progress;
    }

    public int getTotalTopics() {
        return totalTopics;
    }

    public int getSubjectId() {
        return subjectId;
    }
}
