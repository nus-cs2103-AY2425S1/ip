package popi.ui;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import popi.core.Popi;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Popi popi;
    private Ui ui;
    private final Image userImage = loadImage("/images/dinosaur.jpg");
    private final Image popiImage = loadImage("/images/popi.png");
    /**
     * Constructor for MainWindow.
     */
    public MainWindow() {
    }

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        ui = new Ui();
        ui.showWelcome();
        String greeting = ui.getResponse();
        dialogContainer.getChildren().add(
                DialogBox.getPopiDialog(greeting, popiImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Popi instance */
    public void setPopi(Popi d) {
        popi = d;
        popi.setUi(ui);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Popi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (popi == null) {
            System.err.println("Error: Popi instance is not initialized.");
            return;
        }

        String input = userInput.getText();
        String response = popi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPopiDialog(response, popiImage)
        );
        userInput.clear();
    }

    private Image loadImage(String path) {
        InputStream imageStream = this.getClass().getResourceAsStream(path);
        if (imageStream == null) {
            throw new IllegalArgumentException("Image file not found: " + path);
        }
        return new Image(imageStream);
    }
}
