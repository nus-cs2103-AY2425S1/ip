package demurebot;

import demurebot.ui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private DemureBot demureBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image demureBotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     * Binds the scroll pane to the dialog container.
     * Adds a welcome message from DemureBot to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDemureBotDialog("""
                Hi there! I'm DemureBot
                What can I do for you?
                """, demureBotImage)
        );
    }

    /** Injects the DemureBot instance */
    public void setDemureBot(DemureBot d) {
        demureBot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "UserInput TextField is not injected!";
        assert demureBot != null : "DemureBot instance is not set!";

        String input = userInput.getText();
        String response = demureBot.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDemureBotDialog(response, demureBotImage)
        );
        userInput.clear();
        if (response.equals("Bye~~ Hope to see you again soon!")) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }).start();
        }
    }
}

