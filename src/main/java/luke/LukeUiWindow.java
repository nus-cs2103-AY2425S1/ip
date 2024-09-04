package luke;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import luke.command.Command;

public class LukeUiWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Luke luke;

    // Images were taken from the website AI Images (CC BY-ND 4.0).
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ewok.png"));
    private Image lukeImage = new Image(this.getClass().getResourceAsStream("/images/skywalker.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = """
                hey, i'm luke, your glorified task manager.

                i can keep a list of your todos, events, and deadlines.

                tell me what you need to keep track of. i'll help you out :)
                """;
        dialogContainer.getChildren().add(DialogBox.getLukeDialog(welcome, lukeImage));
        try {
            List<String> lines = Storage.loadData();
            for (String line : lines) {
                Command command = Parser.parseSavedData(line);
                Ui.handleCommand(command, true);
            }
        } catch (NoSaveDataFoundException e) {
            handleMissingFile();
        } catch (IOException e) {
            String warning = "hmmm... i ran into an issue while setting up. try launching me again.";
            dialogContainer.getChildren().add(DialogBox.getLukeDialog(warning, lukeImage));
            System.exit(0);
        }
    }

    /**
     * Injects the Duke instance
     */
    public void setLuke(Luke l) {
        luke = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Luke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luke.getOutput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLukeDialog(response, lukeImage)
        );
        userInput.clear();
    }

    /**
     * Provides the user with the choice to create a save file if a save file cannot be found.
     * Exits if an IOException is thrown or if the user chooses not to make a save file.
     */
    public void handleMissingFile() {
        String createSaveFileQuestion = "i couldn't find a saved task list. "
                + "you will need to create one to continue using me.\n"
                + "would you like to create one? (y/n)";
        dialogContainer.getChildren().add(DialogBox.getLukeDialog(createSaveFileQuestion, lukeImage));
        // callback??? the setOnAction code block runs once the input (y/n) is typed and the user presses enter.
        userInput.setOnAction(event -> {
            String input = userInput.getText().strip().toLowerCase();
            String response;
            switch (input) {
            case "y", "yes" -> {
                try {
                    Storage.createSaveFile();
                    response = "save file created! ok, i'm all ears now. tell me what you need.";
                } catch (IOException e) {
                    response = "oof, i couldn't create the file. i'll exit first - try restarting me!";
                    // System.exit(0);
                }
            }
            case "n", "no" -> {
                response = "alright then. cya ;)";
                // System.exit(0);
            }
            default -> {
                response = "didn't quite understand what you said there. try again?";
                handleMissingFile();
            }
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLukeDialog(response, lukeImage)
            );
            userInput.clear();
        });
    }
}
