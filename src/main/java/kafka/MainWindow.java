package kafka;

import javafx.fxml.FXML;
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

    private Kafka kafka;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/StarRailMC.png"));
    private Image kafkaImage = new Image(this.getClass().getResourceAsStream("/images/Kafka.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Kafka instance */
    public void setKafka(Kafka k) {
        kafka = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kafka's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kafka.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, kafkaImage)
        );
        userInput.clear();
    }
}
