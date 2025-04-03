package com.learnerapp.controller;

import com.learnerapp.model.Subject;
import com.learnerapp.model.Topic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import java.util.Optional;


public class SubjectController {
    private final ObservableList<Subject> subjectList;

    public SubjectController() {
        // Initialize with some sample data
        this.subjectList = FXCollections.observableArrayList(
                new Subject("Mathematics", 67, 5, 3, 1),
                new Subject("Physics", 45, 7, 3, 2),
                new Subject("Chemistry", 78, 8, 6, 3),
                new Subject("Biology", 50, 4, 2, 4),
                new Subject("History", 90, 6, 6, 5),
                new Subject("Geography", 40, 5, 2, 6)
        );
    }

    public ObservableList<Subject> getSubjectList() {
        return subjectList;
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public void deleteSubject(Subject subject) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Delete Subject");
        dialog.setHeaderText("Are you sure you want to delete this subject?");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            subjectList.remove(subject);
        }
    }

    public void updateSubject(Subject oldSubject, Subject newSubject) {
        int index = subjectList.indexOf(oldSubject);
        if (index != -1) {
            subjectList.set(index, newSubject);
        }
    }

    public Subject getSubjectById(int subjectId) {
        return subjectList.stream()
            .filter(subject -> subject.getSubjectId() == subjectId)
            .findFirst()
            .orElse(null);
    }
}