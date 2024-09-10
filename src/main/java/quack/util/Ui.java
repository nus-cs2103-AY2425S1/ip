package quack.util;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import quack.gui.DialogBox;
import quack.tasks.Task;

/**
 * This class is responsible for displaying any UI interface,
 * for the Quack chatbot.
 */
public class Ui {

    /** The name of the chatbot */
    private String botName = "Quack";
    /** The logo for Quack */
    private String logo =
        "________                       __    \n"
        + "\\_____  \\  __ _______    ____ |  | __\n"
        + " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n"
        + "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n"
        + "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n"
        + "       \\__>          \\/     \\/     \\/\n";

    /** Farewell message for Quack */
    private String farewellMessage = "Bye. Hope to see you again soon!";
    /** Greeting message for Quack */
    private String greetingMessage = "Hello! I'm " + this.botName + "\nWhat can I do for you?";
    /** Output stream to display response to user */
    private VBox dialogContainer;
    /** Image of the Quack chatbot */
    private Image quackImage;

    /**
     * Creates a Ui object.
     * @param dialogContainer The output stream of the quack.
     * @param quackImage The profile picture of the quack chatbot.
     */
    public Ui(VBox dialogContainer, Image quackImage) {

        this.dialogContainer = dialogContainer;
        this.quackImage = quackImage;
    }

    /**
     * Retrieves the logo of Quack.
     * @return The logo of Quack.
     */
    public String getLogo() {

        return this.logo + "\n";
    }

    /**
     * Retrieves the greeting message for Quack.
     * @return The greeting message to be displayed by Quack
     */
    public String getGreeting() {
        return this.greetingMessage;
    }

    /**
     * Prints the farewell message for Quack.
     */
    public void printFarewell() {

        this.outputToScreen(this.farewellMessage);
    }

    /**
     * Prints the spacer UI element.
     * @param err The exception that was catched by Quack.
     */
    public void printExceptionMessage(Exception err) {

        this.outputToScreen(err.getMessage());
    }

    /**
     * Returns the string representation of the object.
     * @param obj Object to be printed.
     * @return The string representation of the object.
     */
    public String objectToString(Object obj) {

        return obj.toString();
    }

    /**
     * Prints the results of the search.
     * @param filteredTaskList Filtered task list to be printed.
     */
    public void printSearchResult(TaskList filteredTaskList) {

        if (filteredTaskList.getLength() == 0) {
            this.outputToScreen("Im sorry. Seems like no tasks in the task list fits the description!");
        } else {
            String output = ("Here are some tasks that I found that matches your description:\n");
            output += filteredTaskList.toString();
            this.outputToScreen(output);
        }
    }

    /**
     * Prints a comfirmation message to the user once a task is updaed.
     * @param task The task that has been modified.
     * @param command The command that the user entered.
     * @param taskList A list that stores all the tasks tracked by Quack.
     */
    public void printUpdateSuccessfulMessage(Task task, String command, TaskList taskList) {

        this.outputToScreen("Success! I have " + command + "ed this task: " + task.toString() + "\n"
            + "You now have " + taskList.getLength() + " tasks in your list right now!");
    }

    /**
     * Displays prompt to request a search prompt from the user to begin searching
     * for tasks that matches the prompt.
     */
    public void requestSearchPrompt() {

        this.outputToScreen("What task would you like to find?");
    }

    /**
     * Displays prompt to request the user to provide a index input.
     * @param command The command the user has entered.
     */
    public void requestIndexFromUser(String command) {

        String output = "Which task do you want to " + command + "? (Input the index of the task): ";
        this.outputToScreen(output);
    }

    /**
     * Displays prompt to request the user to input a task type.
     */
    public void requestTaskType() {

        this.outputToScreen("What is the type of task you would like to add?");
    }

    /**
     * Displays prompt to request the user to input a task description.
     * @param taskType The task type the user has entered.
     */
    public void requestTaskDescription(String taskType) {

        this.outputToScreen("What is the description for the " + taskType + " task?");
    }

    /**
     * Displays prompt to request the user to input a task description.
     * @param taskType The task type the user has entered.
     */
    public void requestStartDate(String taskType) {

        this.outputToScreen("When is the start date for the " + taskType + " task (Format: DD/MM/YYYY HH:MM:SS)?");
    }

    /**
     * Displays prompt to request the user to input a task description.
     * @param taskType The task type the user has entered.
     */
    public void requestEndDate(String taskType) {

        this.outputToScreen("When is the end date for the " + taskType + " task (Format: DD/MM/YYYY HH:MM:SS)?");
    }

    /**
     * Displays prompt to request the user to input a tag label.
     */
    public void requestTagLabel() {

        this.outputToScreen("What tag label do you want to assign to this task?");
    }

    /**
     * Outputs the text message for quack into the dialog box.
     * @param output The string to be displayed.
     */
    public void outputToScreen(String output) {
        this.dialogContainer.getChildren().addAll(DialogBox.getQuackDialog(output, quackImage));
    }
}
