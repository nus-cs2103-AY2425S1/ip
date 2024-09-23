package nebula.ui;

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

    private Nebula nebula;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image nebulaImage = new Image(this.getClass().getResourceAsStream("/images/DaNebula.jpeg"));

    /**
     * Initializes the Nebula chatbot
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        initializeChatbot();
    }

    /** Injects the Nebula instance */
    public void setNebula(Nebula n) {
        nebula = n;
    }


    /**
     * Initializes the Nebula chatbot with the greeting message
     */
    private void initializeChatbot() {
        Nebula nebula = new Nebula("./data/nebulaTaskList.txt");

        // Display the greeting message at startup
        String greeting = nebula.start();
        dialogContainer.getChildren().addAll(
                DialogBox.getNebulaDialog(greeting, nebulaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    // Then handle user inputs normally with the getResponse method
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = nebula.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNebulaDialog(response, nebulaImage)
        );
        userInput.clear();
    }

}

