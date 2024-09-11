package boss.GUI;

import boss.Boss;
import boss.GUI.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;



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

    private Boss boss;

    private Image bossImage = new Image(this.getClass().getResourceAsStream("/images/boss.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Boss instance */
    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Boss's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String bossText = boss.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getBossDialog(bossText, bossImage));
        userInput.clear();
    }
}
