package jarvis.ui;

import jarvis.logic.Jarvis;
import jarvis.logic.Storage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    private Jarvis jarvis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.jpg"));
    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/jarvis.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String logo =
                "       _                  _     \n" +
                        "      (_)___ _______   __(_)____\n" +
                        "     / / __ `/ ___/ | / / / ___/\n" +
                        "    / / /_/ / /   | |/ / (__  )\n" +
                        " __/ /\\__,_/_/    |___/_/____/\n" +
                        "/___/\n";


        dialogContainer.getChildren().add(
                DialogBox.getJarvisDialog("hello from: \n" + logo + " \nHow can I assist you today?", jarvisImage)
        );
        dialogContainer.getChildren().add(
                DialogBox.getJarvisDialog("here are your previous tasks:", jarvisImage)
        );


        Storage storage = Storage.getInstance(); // Get the singleton instance of Storage

        // Retrieve the tasks from the storage file
        try {
            List<String> savedTasks = Files.readAllLines(Paths.get(Storage.LOAD_SAVE));

            // Add each saved task to the dialog container
            for (String task : savedTasks) {
                dialogContainer.getChildren().add(
                        DialogBox.getJarvisDialog(task, userImage)
                );
            }
            storage.clearFile();
        } catch (IOException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getJarvisDialog("An error occurred while loading saved tasks.", jarvisImage)
            );
            e.printStackTrace();
        }
    }


    /** Injects the Duke instance */
    public void setJarvis(Jarvis j) {
        this.jarvis = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if(input.equalsIgnoreCase("bye")){
            Platform.exit();
        }
        String response = jarvis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJarvisDialog(response, jarvisImage)
        );
        userInput.clear();
    }
}