package com.learnerapp.view;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class LoadingScreen extends Application {
    private ProgressBar progressBar;
    private Label progressLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMaximized(true);

        Image logoImage = new Image(getClass().getResource("/LogoLarge.png").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);
        progressBar.getStyleClass().add("progress-bar");

        progressLabel = new Label("0%");
        progressLabel.getStyleClass().add("bold-text");

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(logoView, progressLabel, progressBar);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(vbox);
        root.setStyle("-fx-background-color: #fff");

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("LearnerApp - loading...");
        primaryStage.setScene(scene);
        primaryStage.show();

        simulateLoading(primaryStage);
    }

    private void simulateLoading(Stage primaryStage) {
        // Create a Task to simulate loading
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for(int i = 0; i <= 100; i++){
                    Thread.sleep(30);
                    updateProgress(i, 100);
                }
                return null;
            }
        };

        // Bind progressBar to task progress
        progressBar.progressProperty().bind(task.progressProperty());

        // Bind progress label to display percentage
        progressLabel.textProperty().bind(
                Bindings.createStringBinding(() ->
                                String.format("%.0f%%", progressBar.getProgress() * 100),
                        progressBar.progressProperty())
        );

        // Open the main window when the task is complete
        task.setOnSucceeded(event -> openMainWindow(primaryStage));
        new Thread(task).start();
    }

    private void openMainWindow(Stage primaryStage) {
        try {
            UserDashboard userDashboard = new UserDashboard();
            Stage dashboardStage = new Stage();
            userDashboard.start(dashboardStage);
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
