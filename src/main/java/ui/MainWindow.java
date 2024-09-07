package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.KukiShinobu;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    // all these are references to MainWindow.fxml lol
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private KukiShinobu kukiShinobu;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image kukiShinobuImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the KukiShinobu instance */
    public void setKukiShinobu(KukiShinobu d) {
        kukiShinobu = d;
    }


    /** Injects the Stage instance */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kukiShinobu.getResponse(input); //need a getExit also btw in case the person exits or something
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKukiShinobuDialog(response, kukiShinobuImage)
        );
        userInput.clear();

        // Checks if KukiShinobu::isExit is true, then exit the program if so
        if (kukiShinobu.isExit()) {
            stage.close();
        }
    }
}
