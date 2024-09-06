package lolo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lolo.Lolo;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Label welcomeLabel; // Add a Label for the welcome message

    private Lolo lolo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image loloImage = new Image(this.getClass().getResourceAsStream("/images/Lolo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        setWelcomeMessage(); // Display the welcome message when initializing
    }

    public void setLolo(Lolo lolo) {
        this.lolo = lolo;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lolo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLoloDialog(response, loloImage)
        );
        userInput.clear();
    }

    private void setWelcomeMessage() {
        welcomeLabel.setText("Hello! I'm lolo, your friendly task manager! \nWhat do you want to do today?");
    }
}

