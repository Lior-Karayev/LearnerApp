package com.learnerapp.view.subjects;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.controller.TopicController;
import com.learnerapp.model.Subject;
import com.learnerapp.view.Topics.TopicView;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A card component that displays information about a subject and provides actions to manage it.
 * Each card shows the subject name, progress, and buttons for viewing topics, editing, and deleting.
 */
public class SubjectCard extends VBox {
    private final Subject subject;
    private final SubjectController subjectController;
    private final SubjectManagementView subjectManagementView;

    /**
     * Creates a new SubjectCard for the given subject.
     *
     * @param subject The subject to display
     * @param subjectController The controller for managing subjects
     * @param subjectManagementView The parent view that contains this card
     */
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

    /**
     * Initializes the card's UI components including the subject name, progress bar,
     * and action buttons.
     */
    private void initializeCard() {
        Label subjectName = new Label(subject.getName());
        subjectName.getStyleClass().add("subject-name");

        // Progress bar and progress label
        ProgressBar progressBar = new ProgressBar(subject.getProgress() / 100.0);
        progressBar.setPrefWidth(200);

        Label progressLabel = new Label(subject.getProgress() + "% Completed");
        Label topicInfo = new Label(subject.getTotalTopics() + " Topics | " + subject.getCompletedTopics() + " Completed");

        // Create icons
        FontIcon viewIcon = new FontIcon(FontAwesomeSolid.FOLDER_OPEN);
        viewIcon.setIconSize(16);
        viewIcon.setIconColor(javafx.scene.paint.Color.WHITE);

        FontIcon editIcon = new FontIcon(FontAwesomeSolid.PENCIL_ALT);
        editIcon.setIconSize(16);
        editIcon.setIconColor(javafx.scene.paint.Color.WHITE);

        FontIcon deleteIcon = new FontIcon(FontAwesomeSolid.TRASH_ALT);
        deleteIcon.setIconSize(16);
        deleteIcon.setIconColor(javafx.scene.paint.Color.WHITE);

        // Action buttons with icons
        Button btnView = new Button("View Topics", viewIcon);
        btnView.getStyleClass().addAll("button", "primary-btn", "btn-small");
        btnView.setOnAction(e -> showTopicsView());

        Button btnEdit = new Button("Edit", editIcon);
        btnEdit.getStyleClass().addAll("button", "btn-small", "btn-green");
        btnEdit.setOnAction(e -> System.out.println("Editing " + subject.getName()));

        Button btnDelete = new Button("Delete", deleteIcon);
        btnDelete.getStyleClass().addAll("button", "btn-small", "btn-red");
        btnDelete.setOnAction(e -> deleteSubject());

        HBox buttonBox = new HBox(btnView, btnEdit, btnDelete);
        buttonBox.getStyleClass().add("button-box");

        getChildren().addAll(subjectName, progressBar, progressLabel, topicInfo, buttonBox);
    }

    /**
     * Shows the topics view for this subject in a new window.
     */
    private void showTopicsView() {
        Stage stage = new Stage();
        TopicView topicView = new TopicView(subject, new TopicController());
        stage.setScene(new javafx.scene.Scene(topicView));
        stage.setTitle(subject.getName() + " - Topics");
        stage.show();
    }

    /**
     * Deletes this subject from the system.
     */
    private void deleteSubject() {
        subjectController.deleteSubject(subject);
        subjectManagementView.refreshSubjects();
    }
}
