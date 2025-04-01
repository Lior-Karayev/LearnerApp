package com.learnerapp.view.subjects;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.model.Subject;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AddSubjectDialog extends Dialog<Subject> {
    private final SubjectController subjectController;

    public AddSubjectDialog(SubjectController subjectController) {
        this.subjectController = subjectController;
        
        setTitle("Add New Subject");

        // Set buttons
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Subject Name");

        TextField totalTopicsField = new TextField();
        totalTopicsField.setPromptText("Total Topics");

        VBox dialogContent = new VBox(10, nameField, totalTopicsField);
        dialogContent.setPadding(new Insets(20));
        getDialogPane().setContent(dialogContent);

        // Convert result to Subject object
        setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                // Validate input before converting
                if (!nameField.getText().trim().isEmpty() && !totalTopicsField.getText().trim().isEmpty()) {
                    try {
                        int totalTopics = Integer.parseInt(totalTopicsField.getText().trim());
                        return new Subject(nameField.getText(), 0, totalTopics, 0);
                    } catch (NumberFormatException e) {
                        showAlert("Invalid Input", "Total Topics must be a number");
                    }
                }
            }
            return null;
        });

        // Handle the result
        setOnCloseRequest(event -> {
            Subject result = getResult();
            if (result != null) {
                subjectController.addSubject(result);
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
