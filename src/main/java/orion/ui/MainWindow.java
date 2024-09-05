package orion.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import orion.Orion;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Orion orion;

    private Image userImage;
    private Image orionImage;

    public MainWindow() {
        try {
            userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
        } catch (NullPointerException e) {
            System.out.println("Couldn't find user image: " + e.getMessage());
        }

        try {
            orionImage = new Image(this.getClass().getResourceAsStream("/images/orion.png"));
        } catch (NullPointerException e) {
            System.out.println("Couldn't find orion image: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setOrion(Orion o) {
        orion = o;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = orion.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOrionDialog(response, orionImage)
        );
        userInput.clear();
    }
}
