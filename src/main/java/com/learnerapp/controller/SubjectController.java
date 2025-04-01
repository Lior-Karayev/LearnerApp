package com.learnerapp.controller;

import com.learnerapp.model.Subject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubjectController {
    private final ObservableList<Subject> subjectList;

    public SubjectController() {
        // Initialize with some sample data
        this.subjectList = FXCollections.observableArrayList(
                new Subject("Mathematics", 67, 5, 3),
                new Subject("Physics", 45, 7, 3),
                new Subject("Chemistry", 78, 8, 6),
                new Subject("Biology", 50, 4, 2),
                new Subject("History", 90, 6, 6),
                new Subject("Geography", 40, 5, 2)
        );
    }

    public ObservableList<Subject> getSubjectList() {
        return subjectList;
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public void deleteSubject(Subject subject) {
        subjectList.remove(subject);
    }

    public void updateSubject(Subject oldSubject, Subject newSubject) {
        int index = subjectList.indexOf(oldSubject);
        if (index != -1) {
            subjectList.set(index, newSubject);
        }
    }
}
