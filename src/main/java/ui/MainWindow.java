package ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Delphi delphi;
    private final Image delphiImage = new Image(this.getClass().getResourceAsStream("/images/delphi.jpeg"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/asian.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Delphi instance
     */
    public void setDelphi(Delphi d) {
        delphi = d;
    }

    /**
     * display the Delphi Welcome message and current tasks loaded from hard drive
     */
    public void welcomeAndCurrentTasks() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDelphiDialog(delphi.welcomeMessage(), delphiImage),
                DialogBox.getDelphiDialog(delphi.currentTasks(), delphiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Delphi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = delphi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDelphiDialog(response, delphiImage)
        );
        if (response.equals("Bye. Hope to see you again soon!")) {
            closeApp();
        }
        userInput.clear();
    }

    /**
     * helper function close the app upon receive the bye command
     */
    private void closeApp() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close(); // Close the JavaFX application
        });
        pause.play(); // Start the pause transition
    }
}

