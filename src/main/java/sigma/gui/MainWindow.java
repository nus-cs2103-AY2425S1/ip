package sigma.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sigma.Sigma;
import sigma.utils.Ui;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image sigmaImage = new Image(this.getClass().getResourceAsStream("/images/sigma.png"));

    public MainWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        fxmlLoader.setRoot(this); // Set the root
        fxmlLoader.setController(this); // Set the controller, optional if it's already set in FXML
        try {
            fxmlLoader.load(); // Load the FXML file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getSigmaDialog(ui.welcome(), sigmaImage, "welcome")
        );

    }

    /** Injects the Sigma instance */
    public void setSigma(Sigma s) {
        sigma = s;
        this.ui = sigma.getUi();
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sigma.getResponse(input);
        String commandType = sigma.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSigmaDialog(response, sigmaImage, commandType)
        );
        userInput.clear();
    }


}
