package yapyap;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private YapperBot yapperBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image yapperBotImage = new Image(this.getClass().getResourceAsStream("/images/YapperBot.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYapperBot(YapperBot yapperBot) {
        this.yapperBot = yapperBot;
    }

    @FXML
    private void handleUserInput() {
        assert userInput != null : "UserInput should not be null";

        String input = userInput.getText();
        assert input != null : "User input text should not be null";

        String response = yapperBot.getResponse(input);
        assert response != null : "Response from YapperBot should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapperBotDialog(response, yapperBotImage)
        );
        userInput.clear();
    }
}
