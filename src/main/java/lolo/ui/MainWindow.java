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

    private Lolo lolo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image loloImage = new Image(this.getClass().getResourceAsStream("/images/Lolo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
}

