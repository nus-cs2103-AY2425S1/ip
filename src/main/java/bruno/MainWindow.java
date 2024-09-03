package bruno;

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
    private Bruno bruno;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mista.png"));
    private Image brunoImage = new Image(this.getClass().getResourceAsStream("/images/bruno.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBruno(Bruno b) {
        bruno = b;
    }

    public void showGreeting() {
        dialogContainer.getChildren().addAll(DialogBox.getBrunoDialog("Hello, I'm Bruno Bucciarati. "
                        + "What can I do for you?", brunoImage, "AddCommand")
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bruno.getResponse(userInput.getText());
        String commandType = bruno.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBrunoDialog(response, brunoImage, commandType)
        );
        userInput.clear();
    }
}
