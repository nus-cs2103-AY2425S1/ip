package wolfie;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wolfie.control.DialogBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    public void setWolfie(Wolfie w) {
        wolfie = w;
    }

    /**
     * Displays the welcome message from Wolfie.
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

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null.";
        assert !input.trim().isEmpty() : "User input should not be empty.";

        System.out.println("User input: " + input); // Debugging statement
        String response;
        boolean isError = false;
        try {
            response = wolfie.getResponse(input);
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
            isError = true;
            e.printStackTrace(); // Print stack trace for debugging
        }
        System.out.println("Wolfie response: " + response); // Debugging statement
        DialogBox dialogBox = isError ? DialogBox.getErrorDialog(response, wolfieImage)
                : DialogBox.getWolfieDialog(response, wolfieImage);
        if (isError) {
            dialogBox.getStyleClass().add("error-text");
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                dialogBox
        );
        userInput.clear();
        if (input.trim().equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }
}
