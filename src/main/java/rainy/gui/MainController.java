package rainy.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import rainy.database.Rainy;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;

import java.io.IOException;

public class MainController extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Rainy rainy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image rainyImage = new Image(this.getClass().getResourceAsStream("/images/rainybot.png"));

    @FXML
    public void initialize() {
        scrollPane.setVvalue(1.0);
    }

    public void setRainy(Rainy r) {
        this.rainy = r;
    }

    @FXML
    private void handleUserInput() throws InvalidIndexException, InvalidMarkAndUnmarkException, IOException {
        String scanCommand = userInput.getText();
        rainy.acceptInput(scanCommand);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(scanCommand, userImage));
        dialogContainer.getChildren().addAll(DialogBox.getRainyDialog(scanCommand, rainyImage));
        userInput.clear();
        scrollPane.setVvalue(scrollPane.getVmax());
    }
}
