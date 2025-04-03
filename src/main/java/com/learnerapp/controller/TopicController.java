package com.learnerapp.controller;

import com.learnerapp.model.Topic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TopicController {

    private final ObservableList<Topic> topicList;

    public TopicController() {
        this.topicList = FXCollections.observableArrayList(
            new Topic(1, 1, "Topic 1", "In Progress"),
            new Topic(2, 1, "Topic 2", "Completed"),
            new Topic(3, 2, "Topic 3", "In Progress"),
            new Topic(4, 2, "Topic 4", "Completed"),
            new Topic(5, 3, "Topic 5", "In Progress"),
            new Topic(6, 3, "Topic 6", "Completed")
        );
    }

    public ObservableList<Topic> getTopicListForSubject(int subjectId) {
        return topicList.filtered(topic -> topic.getSubjectId() == subjectId);
    }
}
