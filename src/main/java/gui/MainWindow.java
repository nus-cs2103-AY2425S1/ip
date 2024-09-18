package gui;

import java.util.HashMap;

import chatterbox.ChatterboxGui;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



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

    private ChatterboxGui chatter;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_image.png"));
    private Image chatterImage = new Image(this.getClass().getResourceAsStream("/images/Chatterbox_image.jpg"));


    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setChatterbox(ChatterboxGui c) {

        chatter = c;
        if (c.hasTasks()) {
            dialogContainer.getChildren()
                    .addAll(DialogBox.getChatterboxDialog("Hmm... Have we met before?", chatterImage),
                            DialogBox.getChatterboxDialog(c.getGreeting(), chatterImage));
        } else {
            dialogContainer.getChildren()
                    .addAll(DialogBox.getChatterboxDialog(c.getGreeting(), chatterImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        HashMap<String, String> response = chatter.processInput(input);

        if (response.get("response") == (null)) {
            DialogBox chatterBye = DialogBox.getChatterboxDialog(chatter.getGoodbye(), chatterImage);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    chatterBye
            );
            userInput.clear();
            PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
            pause.setOnFinished(event -> {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            pause.play();
            return;
        }

        DialogBox chatterReply = DialogBox.getChatterboxDialog(response.get("response"), chatterImage);
        if (response.get("type").equals("ERROR")) {
            for (Node node : chatterReply.getChildren()) {
                node.setStyle("-fx-background-color: #FFCCBB");
            }
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                chatterReply
        );
        userInput.clear();
    }


}
