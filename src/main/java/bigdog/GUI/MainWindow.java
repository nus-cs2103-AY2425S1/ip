package bigdog.GUI;

import bigdog.Bigdog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bigdog bigdog;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/po.png")));
    private Image bigdogImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/oog.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bigdog instance */
    public void setBigdog(Bigdog bot) {
        bigdog = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String bigdogText = bigdog.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBigdogDialog(bigdogText, bigdogImage)
        );
        userInput.clear();
    }

}
