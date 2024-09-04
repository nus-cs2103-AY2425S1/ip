package victor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import victor.commands.Command;
import victor.controls.DialogBox;
import victor.controls.MainWindow;
import victor.messages.ReturnMessage;
import victor.parser.Parser;
import victor.storage.Storage;
import victor.tasklist.TaskList;
import victor.ui.UI;

/**
 * Main class that extends Java FX application.
 */
public class Main extends Application {
    private final Path filePath = Paths.get("data", "data.txt");
    private Parser parser;
    private UI ui;
    private TaskList taskList;
    private Storage storage;
    // JavaFX UI Components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/toucan.png"));
    private Image victorImage = new Image(this.getClass().getResourceAsStream("/images/victor.png"));
    private UI testUI = new UI();

    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * TODO Update comment
     * Initialises parser, UI, storage, and task list elements for the whole program.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUi(testUI);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create dialog box containing user input, append it to dialog container, and clear
     * user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String victorText = testUI.getResponse(userText);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, userImage),
            DialogBox.getVictorDialog(victorText, victorImage));
        userInput.clear();
    }

    private void open() {
        // On start, need to create parser, load data from storage to make Task List
        parser = new Parser();
        ui = new UI();
        storage = new Storage(filePath);
        taskList = storage.load();
        ui.showWelcomeMessage();
    }

    /**
     * Starts program loop and calls initialising and ending functions.
     */
    private void run() {
        open();
        runUntilExit();
        exit();
    }

    /**
     * Runs a program loop that continually received user input until the UI receives a "bye" command.
     */
    private void runUntilExit() {
        Command nextCommand;
        do {
            nextCommand = parser.parseInput(ui.getUserInput());
            nextCommand.setData(taskList);
            ReturnMessage returnMessage = nextCommand.execute();
            ui.showUserMessage(returnMessage);
            nextCommand.write(filePath);
        } while (!nextCommand.isExit());
    }

    /**
     * Calls the UI to generate a parting message and terminates the program.
     */
    private void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

}
