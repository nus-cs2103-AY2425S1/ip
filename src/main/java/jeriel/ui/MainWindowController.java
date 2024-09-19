package jeriel.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {

    @FXML
    private TextArea displayArea;
    
    @FXML
    private TextField inputField;

    @FXML
    public void handleUserInput() {
        String input = inputField.getText();
        String response = getResponse(input); // Call Duke's logic here
        displayArea.appendText("User: " + input + "\n");
        displayArea.appendText("Duke: " + response + "\n");
        inputField.clear();
    }

    private String getResponse(String input) {
        // Implement Duke chatbot response logic here
        return "This is Duke's response!";
    }
}
