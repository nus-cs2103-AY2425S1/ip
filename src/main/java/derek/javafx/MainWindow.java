package derek.javafx;
import derek.Derek;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeParseException;

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
    private Derek derek;
    private Ui ui;
    private String userName = "unnamed user";
    private boolean isWaitingforUserName;
    private boolean isWaitingforResponse;
    private boolean isWaitingforConsent;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image derekImage = new Image(this.getClass().getResourceAsStream("/images/derek.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDerek(Derek d) {
        derek = d;
        this.introduceDerek();
    }

    @FXML
    private void introduceDerek() {
        ui = derek.handleUserInteraction();
        String introduceDerek = ui.introduce();
        dialogContainer.getChildren().add(
                DialogBox.getDerekDialog(introduceDerek, derekImage)
        );
        isWaitingforResponse = true;
        isWaitingforConsent = true;
        isWaitingforUserName = false;
    }

    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = "";

            if (isWaitingforUserName) {
                this.userName = input;
                response = this.ui.initiateUserInteraction(this.userName);
                this.isWaitingforUserName = false;
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(this.userName, input, userImage),
                        DialogBox.getDerekDialog(response, derekImage)
                );
                userInput.clear();
            } else if (isWaitingforConsent) {
                response = this.ui.processConsent(input);
                isWaitingforConsent = false;
                isWaitingforUserName = true;
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(this.userName, input, userImage),
                        DialogBox.getDerekDialog(response, derekImage)
                );
                userInput.clear();
            } else if (isWaitingforResponse) {
                response = this.ui.processCommands(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(this.userName, input, userImage),
                        DialogBox.getDerekDialog(response, derekImage)
                );
                userInput.clear();
            }
        } catch (IncorrectCommandException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(this.userName, userInput.getText(), userImage),
                    DialogBox.getDerekDialog(e.getMessage(), derekImage)
            );
            userInput.clear();
        } catch (DateTimeParseException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(this.userName, userInput.getText(), userImage),
                    DialogBox.getDerekDialog("Please enter your date in the correct format: " +
                            "DD/MM/YYYY HH:MM", derekImage)
            );
            userInput.clear();
        }

    }


}
