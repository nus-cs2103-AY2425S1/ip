package mainwindow;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mollyexception.MollyException;
import ui.DialogBox;
import ui.Ui;

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

    private Molly mollyBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.jpg"));
    private Image mollyImage = new Image(this.getClass().getResourceAsStream("/images/robotimage.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getMollyDialog(Ui.greetUser(), mollyImage));
    }

    /** Injects the Molly instance */
    public void setMolly(Molly mollyBot) {
        this.mollyBot = mollyBot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws MollyException {
        String userText = userInput.getText();

        if (userText.toLowerCase().equals("bye")) {
            String mollyText = Ui.sayBye();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getMollyDialog(mollyText, mollyImage)
            );
            userInput.clear();

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            return;
        }

        String mollyText;
        try {
            mollyText = mollyBot.assistUser(userInput.getText());
        } catch (MollyException e) {
            mollyText = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getMollyDialog(mollyText, mollyImage)
        );
        userInput.clear();
    }
}
