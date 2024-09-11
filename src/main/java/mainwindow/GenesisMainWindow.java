package mainwindow;

import dialogbox.DialogBox;
import genesis.Genesis;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Scanner;

/**
 * Controller for the main GUI.
 */
public class GenesisMainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Genesis genesis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image genesisImage = new Image(this.getClass().getResourceAsStream("/images/jorda.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setGenesis(Genesis g) {
        genesis = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        /*Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            genesis.ui.handleInput(input, false);
        }*/

        String input = userInput.getText();
        String response = genesis.ui.handleInput(input, false);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGenesisDialog(response, genesisImage)
        );
        userInput.clear();
    }
}
