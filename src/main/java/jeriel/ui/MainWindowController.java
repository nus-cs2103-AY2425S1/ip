package jeriel.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jeriel.Jeriel;

public class MainWindowController {

    @FXML
    private TextArea displayArea;

    @FXML
    private TextField inputField;

    private Jeriel jeriel = new Jeriel("data/tasks.txt");

    /**
     * Handles the user input when the "Send" button is clicked.
     */
    @FXML
    private void handleUserInput() {
        String input = inputField.getText();
        String response = jeriel.handleCommand(input); // Use handleCommand to process input
        displayArea.appendText("User: " + input + "\n");
        displayArea.appendText("Duke: " + response + "\n");
        inputField.clear();
    }
}
