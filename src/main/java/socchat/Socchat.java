package socchat;

import Parser.Parser;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import socchat.storage.Storage;

/**
 * Represents the main class for the Socchat application.
 * This class handles user interaction, manages tasks, and processes commands.
 */
public class Socchat {

    private TaskList taskList;

    /**
     * Constructs a Socchat instance with the specified file path.
     * Initializes the user interface, storage, and task list.
     *
     */
    public Socchat() {
        Ui ui = new Ui();
        new Storage("tasks.txt");
        try {
            // Load tasks history into taskList
            taskList = new TaskList(Storage.processStorageLine());
        } catch (SocchatException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat mess age.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(taskList);
        String[] strToken = Parser.extractCommand(input);
        return parser.getResponse(strToken);
    }

    /**
     * Prints a greeting message to the user.
     */
    public static String greet() {
        String respond = "";
        respond += ("Hello! I'm Socchat!\n");
        respond += ("What can I do for you?\n");
        return respond;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static String exit() {

        // Close javafx application after 1.5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return "Bye. Hope to see you again soon!";
    }

}

