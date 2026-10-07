package com.mycompany.studentmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Controller for Add Grades screen
 * Handles saving grades + navigation
 */
public class AddGradesController {

    // Input: Student ID
    @FXML
    private TextField studentIdField;

    // Input: Grade
    @FXML
    private TextField gradeField;

    // Back button
    @FXML
    private Button backButton;

    /*
     * Save grade to file after validation
     */
    @FXML
    private void saveGrade() {

        String studentId = studentIdField.getText().trim();
        String gradeText = gradeField.getText().trim();

        // Check empty fields
        if (studentId.isEmpty() || gradeText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Fill all fields");
            return;
        }

        try {
            int grade = Integer.parseInt(gradeText);

            // Validate range
            if (grade < 0 || grade > 100) {
                showAlert(Alert.AlertType.ERROR, "Error", "Grade must be 0-100");
                return;
            }

            // Save to file
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter("grades.txt", true))) {

                writer.write(studentId + "," + grade);
                writer.newLine();

                // Success message
                showAlert(Alert.AlertType.INFORMATION, "Success", "Grade saved");

                // Reset fields
                clearFields();
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Enter valid number");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "File error");
        }
    }

    /*
     * Go back to dashboard
     */
    @FXML
    private void goBack() {

        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/views/Dashboard.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage =
                    (Stage) backButton.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Clear input fields
     */
    private void clearFields() {
        studentIdField.clear();
        gradeField.clear();
    }

    /*
     * Show alert messages
     */
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}