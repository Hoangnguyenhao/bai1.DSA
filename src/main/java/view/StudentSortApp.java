package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Student;
import model.SortUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentSortApp extends Application {

    private List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        TextField nameField = new TextField();
        nameField.setPromptText("Student's name");
        TextField ageField = new TextField();
        ageField.setPromptText("Student's age");
        TextField scoreField = new TextField();
        scoreField.setPromptText("Student's score");

        Button addButton = new Button("Add Student");
        Button sortButton = new Button("Sort by Score");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPromptText("Student list will appear here...");

        // Add student when button is clicked
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double score = Double.parseDouble(scoreField.getText());

            studentList.add(new Student(name, age, score));

            // Update the result area
            updateResultArea(resultArea, studentList);

            // Clear input fields
            nameField.clear();
            ageField.clear();
            scoreField.clear();
        });

        // Sort students by score when button is clicked
        sortButton.setOnAction(e -> {
            // Sort students using the BubbleSort algorithm
            SortUtils.bubbleSort(studentList);

            // Update the result area after sorting
            updateResultArea(resultArea, studentList);
        });

        // Layout the UI
        VBox vbox = new VBox(10, nameField, ageField, scoreField, addButton, sortButton, resultArea);
        vbox.setPadding(new Insets(15));
        vbox.setMinWidth(300);

        // Set up the scene
        Scene scene = new Scene(vbox, 350, 400);
        primaryStage.setTitle("Student Score Sorter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to update the result area with the student list
    private void updateResultArea(TextArea resultArea, List<Student> studentList) {
        StringBuilder resultText = new StringBuilder();
        for (Student student : studentList) {
            resultText.append(student).append("\n");
        }
        resultArea.setText(resultText.toString());
    }
}
