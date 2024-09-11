import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import snowy.Snowy;

public class MainWindow extends AnchorPane {

    private Snowy snowy;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Image humanImage = new Image(this.getClass().getResourceAsStream("images/human.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getHumanDialog(input, humanImage));
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(snowy.getResponse(input), botImage));
        userInput.clear();
    }

    public void setSnowy(Snowy snowy) {
        this.snowy = snowy;

    }

    public void setInitialMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(snowy.getResponse("hello"), botImage));
    }
}
