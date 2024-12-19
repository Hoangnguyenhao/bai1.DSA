package model;
import java.util.List;

public class SortUtils {

    // Bubble Sort algorithm to sort students by score in descending order
    public static void bubbleSort(List<Student> students) {
        int n = students.size();  // Get the size of the student list

        // Outer loop: iterate through all the elements
        for (int i = 0; i < n - 1; i++) {
            // Inner loop: compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // Compare the score of the current student with the next student
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    // Swap if the current student's score is less than the next student's score
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}