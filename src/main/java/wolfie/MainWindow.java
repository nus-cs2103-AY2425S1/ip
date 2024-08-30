package wolfie;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wolfie.control.DialogBox;

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

    private Wolfie wolfie;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image wolfieImage = new Image(this.getClass().getResourceAsStream("/images/Wolfie.png"));
    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /** Injects the Wolfie instance */
    public void setWolfie(Wolfie w) {
        wolfie = w;
    }
    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        String wolfieArt =
                """
                         __        __   _ _  __ _\s
                         \\ \\      / /__| | |/ _| |
                          \\ \\ /\\ / / _ \\ | | |_| |
                           \\ V  V /  __/ | |  _|_|
                            \\_/\\_/ \\___|_|_|_| (_)
                        """;
        String welcomeMessage = "____________________________________________________________\n"
                + wolfieArt
                + "\n"
                + " Hello Dean's Lister! I'm Wolfie\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        dialogContainer.getChildren().add(DialogBox.getWolfieDialog(welcomeMessage, wolfieImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Wolfie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        System.out.println("User input: " + input); // Debugging statement
        String response = wolfie.getResponse(input);
        System.out.println("Wolfie response: " + response); // Debugging statement
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWolfieDialog(response, wolfieImage)
        );
        userInput.clear();
    }
}
