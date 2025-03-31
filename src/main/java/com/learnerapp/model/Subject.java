package com.learnerapp.model;

import javafx.beans.property.SimpleStringProperty;

public class Subject {
    private final SimpleStringProperty name;
    private final int progress;
    private final int totalTopics;
    private final int completedTopics;

    public Subject(String name, int progress, int totalTopics, int completedTopics) {
        this.name = new SimpleStringProperty(name);
        this.progress = progress;
        this.totalTopics = totalTopics;
        this.completedTopics = completedTopics;
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
}
