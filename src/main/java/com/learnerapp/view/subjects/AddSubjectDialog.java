package com.learnerapp.view.subjects;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.model.Subject;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Random;

public class AddSubjectDialog extends Dialog<Subject> {
    private final TextField nameField;
    private final Spinner<Integer> totalTopicsSpinner;
    private final Spinner<Integer> completedTopicsSpinner;
    private final Random random = new Random();

    public AddSubjectDialog(SubjectController subjectController) {
        setTitle("Add New Subject");
        setHeaderText("Enter subject details");

        // Create the custom dialog pane
        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the grid pane and add the fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Name field
        nameField = new TextField();
        nameField.setPromptText("Subject Name");
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        // Total topics spinner
        totalTopicsSpinner = new Spinner<>(1, 100, 1);
        grid.add(new Label("Total Topics:"), 0, 1);
        grid.add(totalTopicsSpinner, 1, 1);

        // Completed topics spinner
        completedTopicsSpinner = new Spinner<>(0, 100, 0);
        grid.add(new Label("Completed Topics:"), 0, 2);
        grid.add(completedTopicsSpinner, 1, 2);

        // Create icons
        FontIcon saveIcon = new FontIcon(FontAwesomeSolid.SAVE);
        saveIcon.setIconSize(16);
        saveIcon.setIconColor(javafx.scene.paint.Color.WHITE);

        FontIcon cancelIcon = new FontIcon(FontAwesomeSolid.TIMES);
        cancelIcon.setIconSize(16);
        cancelIcon.setIconColor(javafx.scene.paint.Color.WHITE);

        // Custom buttons
        Button okButton = new Button("Save", saveIcon);
        okButton.getStyleClass().addAll("button", "primary-btn");
        Button cancelButton = new Button("Cancel", cancelIcon);
        cancelButton.getStyleClass().addAll("button", "btn-red");

        HBox buttonBox = new HBox(10, okButton, cancelButton);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        grid.add(buttonBox, 0, 3, 2, 1);

        // Set the dialog pane content
        dialogPane.setContent(grid);

        // Convert the result to a Subject object when the OK button is clicked
        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    int totalTopics = totalTopicsSpinner.getValue();
                    int completedTopics = completedTopicsSpinner.getValue();
                    int progress = (int) ((completedTopics * 100.0) / totalTopics);
                    return new Subject(name, progress, totalTopics, completedTopics, random.nextInt(1000));
                }
            }
            return null;
        });

        // Set up button actions
        okButton.setOnAction(e -> {
            if (nameField.getText().trim().isEmpty()) {
                showAlert("Error", "Please enter a subject name.");
                return;
            }
            String name = nameField.getText().trim();
            int totalTopics = totalTopicsSpinner.getValue();
            int completedTopics = completedTopicsSpinner.getValue();
            int progress = (int) ((completedTopics * 100.0) / totalTopics);
            setResult(new Subject(name, progress, totalTopics, completedTopics, random.nextInt(1000)));
            close();
        });

        cancelButton.setOnAction(e -> {
            setResult(null);
            close();
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
