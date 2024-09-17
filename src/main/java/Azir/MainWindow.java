package Azir;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Azir azir;

    private Image userImage = new Image(this.getClass().
            getResourceAsStream("/images/Order_Minion_Caster_Render.png"));
    private Image azirImage = new Image("/images/Azir_0.jpg");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Azir instance
     */
    public void setAzir(Azir a) {
        azir = a;

        String greeting = Ui.showWelcome();
        dialogContainer.getChildren().add(DialogBox.getAzirDialog(greeting, azirImage));

        String loadMessage = azir.getLoadMessage();
        if (!loadMessage.isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getAzirDialog(loadMessage, azirImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Azir's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = azir.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getAzirDialog(response, azirImage)
            );
            userInput.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

