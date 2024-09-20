package pacman;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pacman.gui.DialogBox;

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
    private Scene scene;

    private Pacman pacman;

    private Image ghostImage = new Image(this.getClass().getResourceAsStream("/images/Ghost.png"));
    private Image pacmanImage = new Image(this.getClass().getResourceAsStream("/images/Pacman.png"));
    private String greet = "Hello! I'm PacMan. How can I help you?";

    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getPacmanDialog(greet, pacmanImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPacman(Pacman p) {
        pacman = p;
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String pacmanText = pacman.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getGhostDialog(userText, ghostImage),
                DialogBox.getPacmanDialog(pacmanText, pacmanImage)
        );
        userInput.clear();
        if (userText.equals("bye")) {
            Platform.exit();
        }
    }
}
