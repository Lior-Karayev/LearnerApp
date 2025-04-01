package com.learnerapp.view.subjects;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.model.Subject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SubjectCard extends VBox {
    private final Subject subject;
    private final SubjectController subjectController;
    private final SubjectManagementView subjectManagementView;

    public SubjectCard(Subject subject, SubjectController subjectController, SubjectManagementView subjectManagementView) {
        this.subject = subject;
        this.subjectController = subjectController;
        this.subjectManagementView = subjectManagementView;
        
        setPrefWidth(Region.USE_COMPUTED_SIZE);
        setMaxWidth(Double.MAX_VALUE);
        getStyleClass().add("subject-card");
        setAlignment(Pos.CENTER);
        setSpacing(10);

        initializeCard();
    }

    private void initializeCard() {
        Label subjectName = new Label(subject.getName());
        subjectName.getStyleClass().add("subject-name");

        // Progress bar and progress label
        ProgressBar progressBar = new ProgressBar(subject.getProgress() / 100.0);
        progressBar.setPrefWidth(200);

        Label progressLabel = new Label(subject.getProgress() + "% Completed");
        Label topicInfo = new Label(subject.getTotalTopics() + " Topics | " + subject.getCompletedTopics() + " Completed");

        // Action buttons
        Button btnView = new Button("View Topics");
        btnView.getStyleClass().addAll("button", "primary-btn", "btn-small");
        btnView.setOnAction(e -> System.out.println("Viewing topics for " + subject.getName()));

        Button btnEdit = new Button("Edit");
        btnEdit.getStyleClass().addAll("button", "btn-small", "btn-green");
        btnEdit.setOnAction(e -> System.out.println("Editing " + subject.getName()));

        Button btnDelete = new Button("Delete");
        btnDelete.getStyleClass().addAll("button", "btn-small", "btn-red");
        btnDelete.setOnAction(e -> deleteSubject());

        HBox buttonBox = new HBox(btnView, btnEdit, btnDelete);
        buttonBox.getStyleClass().add("button-box");

        getChildren().addAll(subjectName, progressBar, progressLabel, topicInfo, buttonBox);
    }

    private void deleteSubject() {
        subjectController.deleteSubject(subject);
        subjectManagementView.refreshSubjects();
    }
}
