package cypherchatbot.controller;

import cypherchatbot.Cypher;
import cypherchatbot.components.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Cypher cypher;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userProfile.png"));
    private Image cypherImage = new Image(this.getClass().getResourceAsStream("/images/cypherBot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Cypher instance */
    public void setCypher(Cypher c) {
        this.cypher = c;
        this.cypher.setMainWindow(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert this.cypher != null : "Cypher Instance does not exist";
        String input = userInput.getText();
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        cypher.getResponse(input);
        userInput.clear();
    }

    public void addDialogFromCypher(String response) {
        this.dialogContainer.getChildren().add(DialogBox.getCypherDialog(response, cypherImage));
    }

}
