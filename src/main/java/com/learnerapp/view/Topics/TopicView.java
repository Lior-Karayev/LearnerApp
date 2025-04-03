package com.learnerapp.view.Topics;

import com.learnerapp.controller.TopicController;
import com.learnerapp.model.Subject;
import com.learnerapp.model.Topic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

public class TopicView extends VBox {
    private final TopicController topicController;
    private final Subject subject;

    public TopicView(Subject subject, TopicController topicController) {
        this.subject = subject;
        this.topicController = topicController;
        
        getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        setPadding(new Insets(30));
        setSpacing(20);
        
        setupView();
    }

    private void setupView() {
        // Subject name header
        Label subjectName = new Label(subject.getName());
        subjectName.getStyleClass().add("topic-view__subject-title");

        // Progress section
        ProgressBar progressBar = new ProgressBar(subject.getProgress() / 100.0);
        progressBar.setPrefWidth(400);
        progressBar.getStyleClass().add("progress-bar");

        Label progressLabel = new Label(subject.getProgress() + "% Completed");
        progressLabel.getStyleClass().add("progress-label");

        VBox progressBox = new VBox(10, progressBar, progressLabel);
        progressBox.setAlignment(Pos.CENTER_LEFT);

        // Topics list
        VBox topicsBox = new VBox(15);
        topicsBox.getStyleClass().add("topics-container");

        // Add topics with their status
        for (Topic topic : topicController.getTopicListForSubject(subject.getSubjectId())) {
            HBox topicRow = createTopicRow(topic);
            topicsBox.getChildren().add(topicRow);
        }

        // Watch Course button
        Button watchButton = new Button("Watch Course");
        watchButton.getStyleClass().addAll("button", "primary-btn", "btn-disabled");
        watchButton.setMaxWidth(Double.MAX_VALUE);
        watchButton.setOnAction(e -> handleWatchCourse());

        // Coming Soon section
        HBox comingSoonBox = new HBox(10);
        comingSoonBox.setAlignment(Pos.CENTER);
        
        Label comingSoonLabel = new Label("Coming Soon");
        comingSoonLabel.getStyleClass().add("coming-soon-label");
        
        FontIcon constructionIcon = new FontIcon(FontAwesomeSolid.HARD_HAT);
        constructionIcon.setIconSize(24);
        
        comingSoonBox.getChildren().addAll(comingSoonLabel, constructionIcon);

        // Add all components to the main container
        getChildren().addAll(
            subjectName,
            progressBox,
            topicsBox,
            watchButton,
            comingSoonBox
        );
    }

    private HBox createTopicRow(Topic topic) {
        HBox row = new HBox(20);
        row.setAlignment(Pos.CENTER_LEFT);
        
        // Topic status icon
        FontIcon statusIcon = new FontIcon();
        switch (topic.getStatus().toLowerCase()) {
            case "completed":
                statusIcon.setIconCode(FontAwesomeSolid.CHECK_CIRCLE);
                statusIcon.setIconColor(javafx.scene.paint.Color.GREEN);
                break;
            case "in progress":
                statusIcon.setIconCode(FontAwesomeSolid.CLOCK);
                statusIcon.setIconColor(javafx.scene.paint.Color.ORANGE);
                break;
            default:
                statusIcon.setIconCode(FontAwesomeSolid.CIRCLE);
                statusIcon.setIconColor(javafx.scene.paint.Color.GRAY);
        }
        statusIcon.setIconSize(16);

        // Topic name
        Label topicName = new Label(topic.getName());
        topicName.getStyleClass().add("topic-name");
        HBox.setHgrow(topicName, Priority.ALWAYS);

        // Status label
        Label statusLabel = new Label(topic.getStatus());
        statusLabel.getStyleClass().add("topic-status");

        row.getChildren().addAll(statusIcon, topicName, statusLabel);
        return row;
    }

    private void handleWatchCourse() {
        // TODO: Implement video playback functionality
        System.out.println("Opening video course for: " + subject.getName());
    }
}
