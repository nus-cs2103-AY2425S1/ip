package streams.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import streams.Streams;
import streams.util.Ui;
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
    private Streams stream;
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/streams.png"));
    private final Image streamImage = new Image(getClass().getResourceAsStream("/images/streams2.png"));
    /** Creates the Scroll Pane*/
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().unbind();

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
    }
    /** Injects the streams.Streams instance */
    public void setStream(Streams d) {
        stream = d;
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getStreamsDialog(ui.showWelcome(), streamImage));

    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Streams
     * reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stream.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStreamsDialog(response, streamImage));
        userInput.clear();
        scrollPane.setVvalue(1.0);
    }
}
