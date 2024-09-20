package Johnson.GUI;

import Johnson.command.ExitCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Johnson johnson;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/chief.jpg"));
    private Image johnsonImage = new Image(this.getClass().getResourceAsStream("/images/SargeantJohnson.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getJohnsonDialog(Johnson.greet(), johnsonImage));
    }

    /** Injects the Johnson instance */
    public void setJohnson(Johnson j) {
        johnson = j;
    }

    /**
     * Handles user input and displays the appropriate response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = johnson.getResponse(input);
        if ((response.equals(ExitCommand.COMMAND_MSG))) {
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        dialogContainer.getChildren().addAll(
                DialogBox.getJohnsonDialog(response, johnsonImage)
        );
        userInput.clear();
    }
}
