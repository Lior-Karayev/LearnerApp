package com.learnerapp.view;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.model.Subject;
import com.learnerapp.view.subjects.SubjectManagementView;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserDashboard extends Application {
    private StackPane contentArea; // Main content area
    private SubjectController subjectController;
    private ObservableList<Subject> subjectList;
    private SubjectManagementView subjectManagementView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMaximized(true);

        // Initialize controllers
        subjectController = new SubjectController();
        subjectList = subjectController.getSubjectList();
        subjectManagementView = new SubjectManagementView(subjectList, subjectController);

        // Header with welcome label
        Label welcomeLabel = new Label("Welcome, [User Name]!");
        welcomeLabel.getStyleClass().add("welcome-label");

        // Logo image for navBox
        Image logoImage = new Image(getClass().getResource("/LogoLarge.png").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(120);
        logoView.setPreserveRatio(true);

        // Navigation buttons
        Button btnSubjects = new Button("Manage Subjects");
        Button btnProgress = new Button("View Progress");
        Button btnSettings = new Button("Settings");

        btnSubjects.getStyleClass().addAll("button", "primary-btn", "btn-small");
        btnProgress.getStyleClass().addAll("button", "primary-btn", "btn-small");
        btnSettings.getStyleClass().addAll("button", "primary-btn", "btn-small");

        // Style navigation buttons
        btnSubjects.setPrefWidth(150);
        btnProgress.setPrefWidth(150);
        btnSettings.setPrefWidth(150);

        // VBox for navigation buttons
        VBox navBox = new VBox(15, logoView, btnSubjects, btnProgress, btnSettings);
        navBox.getStyleClass().add("nav-box");

        // Main content area with initial message
        Label contentLabel = new Label("Select an option from the menu");
        contentLabel.setStyle("-fx-font-size: 16px;");
        contentArea = new StackPane(contentLabel);
        contentArea.setPrefSize(600, 400);
        contentArea.getStyleClass().add("content-area");

        // BorderPane layout (Navigation + Content)
        BorderPane root = new BorderPane();
        root.setLeft(navBox);
        root.setCenter(contentArea);
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        BorderPane.setMargin(welcomeLabel, new Insets(10, 0, 10, 0));

        // Set button actions
        btnSubjects.setOnAction(e -> showManageSubjects());
        btnProgress.setOnAction(e -> showInitialMessage());
        btnSettings.setOnAction(e -> showInitialMessage());

        // Scene setup
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("User Dashboard - LearnerApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showManageSubjects() {
        contentArea.getChildren().setAll(subjectManagementView.getView());
    }

    private void showInitialMessage() {
        Label contentLabel = new Label("Select an option from the menu");
        contentLabel.setStyle("-fx-font-size: 16px;");
        contentArea.getChildren().setAll(contentLabel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
