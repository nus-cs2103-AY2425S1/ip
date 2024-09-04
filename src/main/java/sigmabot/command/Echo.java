package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Echo extends Command {
    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        // Set up the input field to capture user input
        inputField.setOnAction(event -> {
            String echoMessage = inputField.getText().trim();  // Read the input from the TextField
            inputField.clear();  // Clear the input field after reading the input
            displayArea.appendText(echoMessage + "\n");  // Echo the message in the display area
        });
    }
}
