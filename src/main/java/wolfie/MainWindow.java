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
    @FXML

    private Wolfie wolfie;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/img.png"));
    private final Image wolfieImage = new Image(this.getClass().getResourceAsStream("/images/Wolfie.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        try {
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            showWelcome();
        } catch (Exception e) {
            System.err.println("Initialisation Error: " + e.getMessage());
        }
    }

    public void setWolfie(Wolfie w) {
        wolfie = w;
    }

    /**
     * Displays the welcome message from Wolfie.
     */
    public void showWelcome() {
        try {
            String wolfieArt =
                    """
                             __        __   _ _  __ _\s
                             \\ \\      / /__| | |/ _| |
                              \\ \\ /\\ / / _ \\ | | |_| |
                               \\ V  V /  __/ | |  _|_|
                                \\_/\\_/ \\___|_|_|_| (_)
                            """;
            String welcomeMessage = wolfieArt
                    + " Hello Dean's Lister! I'm Wolfie, your Personal Assistant!!!\n"
                    + " How can I help you?\n"
                    + "____________________________________________________________";
            dialogContainer.getChildren().add(DialogBox.getWolfieDialog(welcomeMessage, wolfieImage));
        } catch (Exception e) {
            System.err.println("⚠ Welcome Message Error: " + e.getMessage());
        }
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        System.out.println("User input: " + input); // Debugging statement
        String response;

        try {
            response = wolfie.getResponse(input); // Get response from Wolfie
        } catch (Exception e) {
            response = "⚠ " + e.getMessage();
        }
        System.out.println("Wolfie response: " + response); // Debugging statement
        DialogBox dialogBox = DialogBox.getWolfieDialog(response, wolfieImage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                dialogBox
        );
        userInput.clear();
        if (input.trim().equalsIgnoreCase("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000); // Wait for 3 seconds before exiting
                } catch (InterruptedException e) {
                    System.err.println("⚠ Error: " + e.getMessage());
                }
                Platform.exit();
            }).start();
        }
    }
}

