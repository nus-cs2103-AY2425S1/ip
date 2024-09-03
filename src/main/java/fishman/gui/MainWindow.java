package fishman.gui;

import fishman.Fishman;
import fishman.exception.FishmanException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/koi.jpg"));
    private final Image fishmanImage = new Image(this.getClass().getResourceAsStream("/images/fish.jpg"));
    private Fishman fishman;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();

    }

    public void setFishman(Fishman fishman) {
        this.fishman = fishman;
        try {
            fishman.loadTasks();
        } catch (FishmanException e) {
          showLoadErrors(e.getMessage());
        } catch (Exception e) {
            showLoadErrors("An unexpected error has occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fishman.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFishmanDialog(response, fishmanImage)
        );
        userInput.clear();
    }

    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Fishman\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getFishmanDialog(welcomeMessage, fishmanImage));
    }

    private void showLoadErrors(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getFishmanDialog(errorMessage, fishmanImage));
    }


}
