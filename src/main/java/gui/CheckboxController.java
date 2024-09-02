package gui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import regina.Regina;
import tasks.Task;
import tasks.TaskList;

public class CheckboxController {

    @FXML
    private VBox checkboxContainer; // This VBox will hold the dynamically added checkboxes

    private Regina regina;

    @FXML
    public void initialize() {
        // This method will be called when the FXML is loaded
        if (regina != null) {
            createCheckboxesFromTaskList(regina.getListOfTasks());
        }
    }

    public void setRegina(Regina r) {
        regina = r; // Set the Regina instance
        createCheckboxesFromTaskList(regina.getListOfTasks()); // Load tasks into checkboxes
    }

    @FXML
    private void handleClose() {
        // Close the checkbox window
        Stage stage = (Stage) checkboxContainer.getScene().getWindow();
        stage.close();
    }

    // Method to create checkboxes based on the task list
    public void createCheckboxesFromTaskList(TaskList listOfTasks) {
        checkboxContainer.getChildren().clear(); // Clear any existing checkboxes
        int noOfTasks = listOfTasks.size();

        // Iterate through the task list and create a checkbox for each task
        for (int i = 0; i < noOfTasks; i++) {
            Task task = listOfTasks.get(i); // Get the task at index i
            addCheckbox(task.toString()); // Add the checkbox for the task
        }
    }

    // Method to add a checkbox dynamically
    public void addCheckbox(String checkboxText) {
        CheckBox newCheckBox = new CheckBox(checkboxText);
        newCheckBox.setOnAction(e -> {
            System.out.println(newCheckBox.isSelected() ? checkboxText + " selected" : checkboxText + " deselected");
        });
        checkboxContainer.getChildren().add(newCheckBox);
    }

    // Method to handle adding new checkboxes by user input
    @FXML
    private void addCheckboxDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Checkbox");
        dialog.setHeaderText("Enter checkbox label:");
        dialog.setContentText("Label:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(label -> addCheckbox(label));
    }
}
