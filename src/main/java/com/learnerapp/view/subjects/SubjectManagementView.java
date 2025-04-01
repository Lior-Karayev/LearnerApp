package com.learnerapp.view.subjects;

import com.learnerapp.controller.SubjectController;
import com.learnerapp.model.Subject;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SubjectManagementView {
    private final VBox contentBox;
    private final ObservableList<Subject> subjectList;
    private final SubjectController subjectController;
    private GridPane cardGrid;

    public SubjectManagementView(ObservableList<Subject> subjectList, SubjectController subjectController) {
        this.subjectList = subjectList;
        this.subjectController = subjectController;
        this.contentBox = new VBox(20);
        this.contentBox.setAlignment(Pos.CENTER);
        this.contentBox.setPadding(new Insets(20));
        
        initializeView();
    }

    private void initializeView() {
        // Create GridPane for subject cards
        cardGrid = new GridPane();
        cardGrid.setPadding(new Insets(20));
        cardGrid.setHgap(20);
        cardGrid.setVgap(20);
        cardGrid.setAlignment(Pos.CENTER);

        // Button to add subject
        Button btnAddSubject = new Button("+ Add Subject");
        btnAddSubject.setOnAction(e -> showAddSubjectDialog());
        btnAddSubject.getStyleClass().addAll("button", "primary-btn", "btn-semi-bold");

        contentBox.getChildren().addAll(btnAddSubject, cardGrid);
        
        refreshSubjects();
    }

    public void refreshSubjects() {
        // Clear existing cards
        cardGrid.getChildren().clear();

        // Add subject cards to grid
        int column = 0;
        int row = 0;
        for(Subject subject: subjectList) {
            SubjectCard card = new SubjectCard(subject, subjectController, this);
            cardGrid.add(card, column, row);
            column++;
            if(column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private void showAddSubjectDialog() {
        AddSubjectDialog dialog = new AddSubjectDialog(subjectController);
        dialog.showAndWait();
        refreshSubjects(); // Refresh after adding a subject
    }

    public VBox getView() {
        return contentBox;
    }
}
