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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
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
            String welcomeMessage = "____________________________________________________________\n"
                    + wolfieArt
                    + "\n"
                    + " Hello Dean's Lister! I'm Wolfie\n"
                    + " What can I do for you?\n"
                    + "____________________________________________________________";
            dialogContainer.getChildren().add(DialogBox.getWolfieDialog(welcomeMessage, wolfieImage));
        } catch (Exception e) {
            System.err.println("Welcome Message Error: " + e.getMessage());
        }
    }
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        System.out.println("User input: " + input); // Debugging statement
        String response;
        boolean isError = !isValidCommand(input);

        try {
            response = wolfie.getResponse(input);
        } catch (Exception e) {
            isError = true;
            response = "Error: " + e.getMessage();
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
            new Thread(() -> {
                try {
                    Thread.sleep(5000); // Wait for 5 seconds before exiting
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                Platform.exit();
            }).start();
        }
    }
    private boolean isValidCommand(String input) {
        return input.trim().equalsIgnoreCase("bye")
                || input.trim().equalsIgnoreCase("list")
                || input.trim().equalsIgnoreCase("todo")
                || input.trim().equalsIgnoreCase("deadline")
                || input.trim().equalsIgnoreCase("event")
                || input.trim().equalsIgnoreCase("delete")
                || input.trim().equalsIgnoreCase("find")
                || input.trim().equalsIgnoreCase("mark")
                || input.trim().equalsIgnoreCase("unmark")
                || input.trim().equalsIgnoreCase("on");
    }
}

