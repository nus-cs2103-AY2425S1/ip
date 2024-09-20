package bing;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class MainLayout {

    private Bing bing;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBing(Bing b) {
        bing = b;
        String welcomeMessage = bing.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getBingDialog(welcomeMessage));
    }

    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input cannot be empty.";
        String response = bing.byeResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getBingDialog(response)
        );
        userInput.clear();
    }
}
