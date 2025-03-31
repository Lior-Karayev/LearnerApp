package com.learnerapp.view;

import com.learnerapp.model.Subject;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
    private ObservableList<Subject> subjectList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMaximized(true);

        // Header with welcome label
        Label welcomeLabel = new Label("Welcome, [User Name]!");
        welcomeLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Logo image for navBox
        Image logoImage = new Image(getClass().getResource("/LogoLarge.png").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(120);
        logoView.setPreserveRatio(true);

        // Navigation buttons
        Button btnSubjects = new Button("Manage Subjects");
        Button btnProgress = new Button("View Progress");
        Button btnSettings = new Button("Settings");

        // Style navigation buttons
        btnSubjects.setPrefWidth(150);
        btnProgress.setPrefWidth(150);
        btnSettings.setPrefWidth(150);

        // VBox for navigation buttons
        VBox navBox = new VBox(15, logoView, btnSubjects, btnProgress, btnSettings);
        navBox.setAlignment(Pos.TOP_LEFT);
        navBox.setPadding(new Insets(20));

        // Main content area
        Label contentLabel = new Label("Select an option from the menu");
        contentLabel.setStyle("-fx-font-size: 16px;");
        contentArea = new StackPane(contentLabel); // Placeholder for dynamic content
        contentArea.setPrefSize(600, 400);
        contentArea.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20px;");

        // BorderPane layout (Navigation + Content)
        BorderPane root = new BorderPane();
        root.setLeft(navBox);
        root.setCenter(contentArea);
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        BorderPane.setMargin(welcomeLabel, new Insets(10, 0, 10, 0));

        // Set button actions
        btnSubjects.setOnAction(e -> showManageSubjects());

        // Scene setup
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("User Dashboard - LearnerApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showManageSubjects(){
        subjectList = FXCollections.observableArrayList(
                new Subject("Mathematics", 67, 5, 3),
                new Subject("Physics", 45, 7, 3),
                new Subject("Chemistry", 78, 8, 6),
                new Subject("Biology", 50, 4, 2),
                new Subject("History", 90, 6, 6),
                new Subject("Geography", 40, 5, 2)
        );

        // Create GridPane for subject cards
        GridPane cardGrid = new GridPane();
        cardGrid.setPadding(new Insets(20));
        cardGrid.setHgap(20);
        cardGrid.setVgap(20);
        cardGrid.setAlignment(Pos.CENTER);

        // Add subject cards to grid
        int column = 0;
        int row = 0;
        for(Subject subject: subjectList){
            VBox card = createSubjectCard(subject);
            cardGrid.add(card, column, row);
            column++;
            if(column == 3){
                column = 0;
                row++;
            }
        }

        // Button to add subject
        Button btnAddSubject = new Button("+ Add Subject");
        btnAddSubject.setStyle("-fx-font-size: 16px; -fx-background-color: #2563EB; -fx-text-fill: white;");
        btnAddSubject.setOnAction(e -> showAddSubjectDialog());

        VBox contentBox = new VBox(20, btnAddSubject, cardGrid);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(20));

        // Update contentArea with card grid
        contentArea.getChildren().setAll(contentBox);
    }

    private VBox createSubjectCard(Subject subject){
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: #dcdcdc; -fx-border-radius: 10; -fx-padding: 15; -fx-background-color: white;");
        card.setPrefSize(250, 100);

        Label subjectName = new Label(subject.getName());
        subjectName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");

        // Progress bar and progress label
        ProgressBar progressBar = new ProgressBar(subject.getProgress() / 100.0);
        progressBar.setPrefWidth(200);

        Label progressLabel = new Label(subject.getProgress() + "% Completed");

        Label topicInfo = new Label(subject.getTotalTopics() + "Topics | " + subject.getCompletedTopics() + " Completed");

        // Action buttons
        Button btnView = new Button("View Topics");
        btnView.setStyle("-fx-background-color: #2563EB; -fx-text-fill: white;");
        btnView.setOnAction(e -> System.out.println("Viewing topics for " + subject.getName()));

        Button btnEdit = new Button("Edit");
        btnView.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnView.setOnAction(e -> System.out.println("Editing " + subject.getName()));

        Button btnDelete = new Button("Delete");
        btnView.setStyle("-fx-background-color: #FF4D4D; -fx-text-fill: white;");
        btnView.setOnAction(e -> deleteSubject(subject));

        HBox buttonBox = new HBox(btnView, btnEdit, btnDelete);
        buttonBox.setAlignment(Pos.CENTER);

        card.getChildren().addAll(subjectName, progressBar, progressLabel, topicInfo, buttonBox);
        card.setAlignment(Pos.CENTER);
        return card;
    }

    // Show a placeholder message in contentArea
    private void showPlaceholder(String message) {
        Label placeholderLabel = new Label(message);
        placeholderLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #333");
        contentArea.getChildren().setAll(placeholderLabel);
    }

    private void showAddSubjectDialog(){
        Dialog<Subject> dialog = new Dialog<>();
        dialog.setTitle("Add New Subject");

        // Set buttons
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Subject Name");

        TextField totalTopicsField = new TextField();
        totalTopicsField.setPromptText("Total Topics");

        VBox dialogContent = new VBox(10, nameField, totalTopicsField);
        dialogContent.setPadding(new Insets(20));
        dialog.getDialogPane().setContent(dialogContent);

        // Convert result to Subject object
        dialog.setResultConverter(dialogButton -> {
            // Validate input before converting
            if(!nameField.getText().trim().isEmpty() && !totalTopicsField.getText().trim().isEmpty()){
                try {
                    int totalTopics = Integer.parseInt(totalTopicsField.getText().trim());
                    return new Subject(nameField.getText(), 0, totalTopics, 0);
                } catch (NumberFormatException e) {
                    showAlert("invalid Input", "Total Topics must be a number");
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(subject -> {
            subjectList.add(subject);
            showManageSubjects();
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void deleteSubject(Subject subject) {
        subjectList.remove(subject);
        showManageSubjects(); // Refresh grid after deletion
    }

    public static void main(String[] args) {
        launch(args);
    }
}
