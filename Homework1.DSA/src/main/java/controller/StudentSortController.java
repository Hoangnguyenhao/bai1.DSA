package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Student;

import java.util.List;

public class StudentSortController {

    private List<Student> studentList;
    private TextField nameField;
    private TextField idField;
    private TextField scoreField;
    private ListView<String> listView;
    private Button addButton;
    private Button sortButton;

    // Constructor to initialize the controller with UI components
    public StudentSortController(List<Student> studentList, TextField nameField, TextField idField,
                                 TextField scoreField, ListView<String> listView, Button addButton, Button sortButton) {
        this.studentList = studentList;
        this.nameField = nameField;
        this.idField = idField;
        this.scoreField = scoreField;
        this.listView = listView;
        this.addButton = addButton;
        this.sortButton = sortButton;

        // Set up event handlers for buttons
        addButton.setOnAction(e -> handleAddButton());
        sortButton.setOnAction(e -> handleSortButton());
    }

    // Handles the Add Student button click event
    private void handleAddButton() {
        String name = nameField.getText();
        String id = idField.getText();
        double score = 0;

        try {
            score = Double.parseDouble(scoreField.getText());
        } catch (NumberFormatException ex) {
            showErrorDialog("The score must be a valid number.");
            return;
        }

        Student student = new Student(name,id, score);
        studentList.add(student);
        listView.getItems().add(student.toString());

        // Clear input fields after adding
        nameField.clear();
        idField.clear();
        scoreField.clear();
    }

    // Handles the Sort button click event
    private void handleSortButton() {
        bubbleSort(studentList);
        listView.getItems().clear();
        for (Student student : studentList) {
            listView.getItems().add(student.toString());
        }
    }

    // BubbleSort algorithm to sort students by score
    private void bubbleSort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    // Swap
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Show an error dialog with a message
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
