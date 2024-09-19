import bro.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

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

    private Bro bro;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList tasks;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private Image broImage = new Image(this.getClass().getResourceAsStream("/images/DaBro.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Duke instance.
     * This method also sets up the UI, parser, task list, and storage for the application.
     * It also attempts to load any existing data from a file and displays a welcome message.
     *
     * @param b The Bro instance to be set for the application.
     */
    public void setBro(Bro b) {
        bro = b;
        ui = new Ui();
        parser = new Parser(ui);
        tasks = new TaskList(ui, parser);
        storage = new Storage(tasks);
        try {
            storage.loadIn();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nothing to load");
        } catch (BroException be) {
            System.out.println(be.getMessage());
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getBroDialog(ui.printWelcome(), broImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bro.getResponse(input, ui, tasks, storage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBroDialog(response, broImage)
        );
        userInput.clear();
    }
}