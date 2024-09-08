package sumode.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sumode.SumoDE;
import sumode.task.Task;

/**
 * Handles all statements to be printed in SumoDE.
 */
public class Ui {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private HBox heading;

    private SumoDE sumoDE;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/kangaroo.png"));
    private final Image sumoImage = new Image(this.getClass().getResourceAsStream("/images/sumoDE.png"));

    /** Initialise the property for scroll bar to always scroll down and for SumoDE to greet */
    @FXML
    public void initialize() { //the initialize method in Java controller is automatically called after FXML been loaded
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // means auto scroll down
        this.greet();
    }


    /** Injects the SumoDE instance */
    public void setSumoDE() {
        this.sumoDE = new SumoDE("data\\taskSaved.txt", this);
    }

    /**
     * Reply by SumoDE.
     * @param response response given by SumoDE.
     */
    @FXML
    private void respond(String response) {
        dialogContainer.getChildren().addAll(DialogBox.getSumoDialog(response, sumoImage));
    }

    /**
     * Angry reply by SumoDE - usually used when errors are made.
     * @param response response given by SumoDE
     */
    @FXML
    private void respondDanger(String response) {
        dialogContainer.getChildren().addAll(DialogBox.getSumoAngryDialog(response, sumoImage));
    }

    /**
     * Print Message by user for record.
     * @param input input from user
     */
    @FXML
    private void echo(String input) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
    }

    /**
     * Handles the user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        echo(input);
        userInput.clear();
        this.sumoDE.execute(input);
    }

    /**
     * Prints a greeting message to user.
     */
    public void greet() {
        this.respond("""
                ----------------------------------
                      Hello, I am Sumo-DE!
                   How can Sumo help you?
                ----------------------------------"""
        );
    }

    /**
     * Prints a notice that command is wrong.
     */
    public void warnUnknownCommand(String commandString) {
        this.respondDanger("Sumo dunno your command \""
                + commandString
                + "\" ! Check spelling of your first word pleaseeeeeeeeee.....");
    }

    /**
     * Prints error message.
     */
    public void handleError(Exception e) {
        this.respondDanger(e.getMessage());
    }

    /**
     * Prints a query asking for next command.
     */
    public void requestNext() {
        this.respond("""
                            --------------------------------------------
                            Do you need anything else from SUMO?
                            --------------------------------------------""");
    }

    /**
     * Prints goodbye message.
     */
    public void bye() {
        this.respond("""
                ------------------------------------
                Goodbye! Sumo hope to see you again!
                ------------------------------------
                """);
    }

    /**
     * Prints a notice that user won't be able to save any data in this session.
     */
    public void warnUnknownSaveError() {
        this.respondDanger("Help! Sumo unable to save data due to unknown error!\n"
                        + "Please exit and try again if u wanna save");
    }

    /**
     * Prints a notice that user's saved file is corrupted and inform user which line it is at.
     * @param line line where the file is corrupted.
     */
    public void warnCorruptedSaveFile(int line) {
        this.respondDanger("Your saved file at line " + (line) + " is corrupted. "
                        + "Sumo cannot read so Sumo will skip that and continue with the rest!");
    }

    /**
     * Prints all the tasks given in the task list.
     * @param tasks list of task to be printed.
     */
    public void printTask(List<Task> tasks, boolean isItFiltered) {
        StringBuilder response = new StringBuilder();
        if (isItFiltered) {
            response.append("Below is the list of MATCHING tasks based on your request:");
        } else {
            response.append("Below is the list of tasks:\n");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task == null) {
                break;
            }
            response.append('\n').append((i + 1)).append(". ").append(task);
        }
        respond(response.toString());
    }

    /**
     * Prints a notice that user's task is added to the list.
     * @param task task added.
     * @param count total task after adding.
     */
    public void printTaskAdded(Task task, int count) {
        this.respond("Sumo has added this task for you.\n"
                + task
                + "\n"
                + "There are now "
                + count
                + " task(s) in total!");
    }

    /**
     * Prints a notice that user's task is removed from the list.
     * @param task task removed.
     * @param count total task after removing.
     */
    public void printTaskRemoved(Task task, int count) {
        this.respond("Sumo removed this task for you.\n"
                        + task
                        + "\n"
                        + "There are now "
                        + count
                        + " task(s) in total!");
    }

    /**
     * Prints a notice that user's task is unmarked.
     * @param task task unmarked.
     */
    public void printTaskUnmarked(Task task) {
        this.respond("Sumo has marked this task as NOT done.\n" + task);
    }

    /**
     * Prints a notice that user's task is marked.
     * @param task task marked.
     */
    public void printTaskMarked(Task task) {
        this.respond("Sumo has marked this task as done.\n" + task);
    }
}
