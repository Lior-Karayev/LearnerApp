package com.learnerapp.view.subjects;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.model.Subject;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SubjectManagementView extends VBox {
    private final SubjectController subjectController;
    private final GridPane subjectsGrid;

    public SubjectManagementView(SubjectController subjectController) {
        this.subjectController = subjectController;
        this.subjectsGrid = new GridPane();
        
        setSpacing(20);
        setPadding(new Insets(20));
        getStyleClass().add("subject-management-view");

        initializeView();
        refreshSubjects();
    }

    private void initializeView() {
        // Header
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getStyleClass().add("header");

        Label title = new Label("Subject Management");
        title.getStyleClass().add("title");

        FontIcon addIcon = new FontIcon(FontAwesomeSolid.PLUS);
        addIcon.setIconSize(16);
        addIcon.setIconColor(javafx.scene.paint.Color.WHITE);

        Button addButton = new Button("Add Subject", addIcon);
        addButton.getStyleClass().addAll("button", "primary-btn");
        addButton.setOnAction(e -> showAddSubjectDialog());

        header.getChildren().addAll(title, addButton);

        // Subjects grid
        subjectsGrid.setHgap(20);
        subjectsGrid.setVgap(20);
        subjectsGrid.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(subjectsGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("scroll-pane");

        getChildren().addAll(header, scrollPane);
    }

    private void showAddSubjectDialog() {
        AddSubjectDialog dialog = new AddSubjectDialog(subjectController);
        dialog.showAndWait().ifPresent(subject -> {
            subjectController.addSubject(subject);
            refreshSubjects();
        });
    }

    public void refreshSubjects() {
        subjectsGrid.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Subject subject : subjectController.getSubjectList()) {
            subjectsGrid.add(new SubjectCard(subject, subjectController, this), column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }
}
