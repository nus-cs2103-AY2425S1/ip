package jeriel.ui;

import jeriel.Jeriel;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainWindowController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Jeriel jeriel; 

    private ImageView userImage = new ImageView("/images/user.png");
    private ImageView botImage = new ImageView("/images/bot.png");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Adding event listener to listen for 'Enter' key press in userInput TextField
        userInput.setOnAction(event -> handleUserInput());  // Handles enter key press in text field
    }

    public void setJeriel(Jeriel j) {
        this.jeriel = j;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim(); // Trim to remove unnecessary spaces
        if (!input.isEmpty()) {  // Only process input if it's not empty
            String response = jeriel.getResponse(input);

            // Add user and bot dialog to the dialog container
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage.getImage()), 
                DialogBox.getBotDialog(response, botImage.getImage())
            );

            userInput.clear();  // Clear the text field after handling input
        }
    }
}
