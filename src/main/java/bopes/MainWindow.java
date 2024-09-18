package bopes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class MainWindow {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bopes bopes;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bopesImage = new Image(this.getClass().getResourceAsStream("/images/bopes.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBopes(Bopes bopes) {
        this.bopes = bopes;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bopes.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBopesDialog(response, bopesImage)
        );
        VBox.setVgrow(dialogContainer, Priority.ALWAYS);
        userInput.clear();
    }
}
