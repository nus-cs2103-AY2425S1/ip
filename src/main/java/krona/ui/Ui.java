package krona.ui;

import java.util.Scanner;

import javafx.application.Platform;
import javafx.stage.Stage;
import krona.task.TaskList;


/**
 * The Ui class handles interactions with the user.
 * It is responsible for displaying messages, receiving user input,
 * and showing the list of tasks.
 */
public class Ui {
    private Scanner scanner;
    private String lastMessage;
    private String combinedMessage;
    private Stage stage;

    /**
     * Constructs a Ui object and initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public Ui(Stage stage) {
        this.stage = stage;
    }

    public void goodbye() {
        setCombinedMessage("Bye. Hope to see you again soon!");
    }

    public void exitApp() {
        Platform.exit();
    }

    public void showLoadingError() {
        System.out.println("Error loading data from file.");
    }

    public void setCombinedMessage(String message) {
        this.combinedMessage = message;
    }

    public String getCombinedMessage() {
        return this.combinedMessage;
    }

    /**
     * Displays the list of tasks to the user.
     * If the task list is empty, a message is shown indicating this.
     * Otherwise, the tasks in the list are displayed.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("Your list is currently empty.");
        } else {
            message.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        setCombinedMessage(message.toString());
    }
}
