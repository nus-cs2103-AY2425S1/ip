package myapp.core;

import java.util.Objects;

import javafx.fxml.FXML;
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

    private Ruby ruby;

    private Stage stage;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/profile.png")));
    private final Image rubyImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ruby.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setRuby(Ruby r) {
         ruby = r;
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
        String response = ruby.getResponse(input);
        boolean shouldClose = response.equals("bye");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRubyDialog(response, rubyImage)
        );
        userInput.clear();
        if (shouldClose && stage != null) {
            stage.close();
        }
    }
}

