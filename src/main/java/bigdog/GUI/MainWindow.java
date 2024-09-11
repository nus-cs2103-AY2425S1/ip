package bigdog.GUI;

import bigdog.Bigdog;
import bigdog.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;


/**
 * Controller for MainWindow. Provides the layout
 * for the other controls and handles user interactions.
 */

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

    /**
     * Initializes the controller class.
     * Binds the scroll pane to the dialog container's height and adds the initial greeting from the Bigdog bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getBigdogDialog(Ui.greet(), bigdogImage)
        );
    }

    /**
     * Injects the Bigdog instance into this controller.
     * @param bot The Bigdog bot instance.
     */
    public void setBigdog(Bigdog bot) {
        bigdog = bot;
    }

    /**
     * Handles user input by creating dialog boxes for both user input and bot reply.
     * Appends the dialog boxes to the dialog container and clears the user input field.
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
