package sigma.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sigma.Sigma;
import sigma.utils.Ui;


/**
 * Controller for the main GUI.
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
    private Sigma sigma;
    private Ui ui;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image sigmaImage = new Image(this.getClass().getResourceAsStream("/images/sigma.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSigmaDialog(Ui.welcome(), sigmaImage, "welcome")
        );

    }

    /**
     * Injects the Sigma instance
     */
    public void setSigma(Sigma s) {
        sigma = s;
        assert sigma != null : "Sigma should not be null.";
        this.ui = sigma.getUi();
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "Input should not be null.";
        String response = sigma.getResponse(input);
        assert response != null : "Response should not be null.";
        String commandType = sigma.getCommandType();
        assert commandType != null : "Command type should not be null.";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSigmaDialog(response, sigmaImage, commandType)
        );
        userInput.clear();
    }


}
