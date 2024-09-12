package nayana;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import nayana.task.Task;

/**
 * Handles interactions with the user in the console.
 */
public class Ui {
    private Image nayanaDoneImage = new Image(this.getClass().getResourceAsStream("/images/NayanaDone.png"));
    private Image nayanaImage = new Image(this.getClass().getResourceAsStream("/images/Nayana.png"));
    private Scanner scanner;
    private VBox dialogContainer;

    /**
     * Initializes the Ui class with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }


    public void print(String text, Image image) {
        assert dialogContainer != null;
        dialogContainer.getChildren().addAll(
              DialogBox.getNayanaDialog(text, image)
        );
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        String text = "OOPS!!! There was an error loading the tasks. Starting with an empty list.";
        print(text, this.nayanaImage);
    }

    /**
     * Displays the welcome message and application introduction.
     */
    public void showWelcomeMessage() {
        String text = "Hello! I'm Nayana\nWhat can I do for you? ";
        print(text, this.nayanaImage);
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line for separation.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        print(message, this.nayanaDoneImage);
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printAddTask(Task task, int size) {
        String text = "Got it. I've added this task:\n"
        + task
        + "\nNow you have " + size +  " tasks left in the list.";
        print(text, this.nayanaImage);
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void printDeleteTask(Task task, int size) {
        String text = "Noted. I've removed this task:\n"
              + task
              + "\nNow you have " + size +  " tasks left in the list.";
        print(text, this.nayanaImage);
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkTask(Task task) {
        String text = "Nice! I've marked this task as done:\n"
              + task;
        print(text, this.nayanaImage);
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUnmarkTask(Task task) {
        String text = "OK, I've marked this task as not done yet:\n"
              + task;
        print(text, this.nayanaImage);
    }

    /**
     * Prints the entire task list.
     *
     * @param tasks The TaskList object containing the tasks.
     */
    public void printTaskList(TaskList tasks) {
        print(tasks.toString(), this.nayanaImage);
    }

    /**
     * Prints a message indicating that the application is exiting.
     */
    public void printExit() {
        String text = "Bye!!! Hope to help you again soon!";
        print(text, this.nayanaImage);
    }

    /**
     * Prints the list of tasks that match the search criteria.
     *
     * @param foundTasks An ArrayList of tasks that match the search query.
     */
    public void printFoundTasks(ArrayList<Task> foundTasks) {
        String text = "";
        if (foundTasks.size() > 0) {
            text += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < foundTasks.size(); i++) {
               text +=  (i + 1) + "." + foundTasks.get(i) + "\n";
            }
        } else {
            text += "There are no matching tasks in your list.";
        }
        print(text, this.nayanaImage);
    }

    public void setVbox(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }
}
