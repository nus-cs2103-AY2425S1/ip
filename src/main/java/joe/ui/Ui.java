package joe.ui;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import joe.controller.Controller;
import joe.parser.Parser;
import joe.task.Task;

/**
 * Handles the user interface of the program.
 */
public class Ui {
    private MainWindow mainWindow;
    private Stage stage;

    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    private final String CHATBOT_NAME;

    public Ui(String chatbotName) {
        this.CHATBOT_NAME = chatbotName;
    }

    /**
     * Starts the program by displaying the main window.
     * 
     * @param stage The stage to display the main window on.
     * @param parser The parser to use for parsing user input.
     */
    public void start(Stage stage, Parser parser) {
        mainWindow = new MainWindow();
        this.stage = stage;
        Scene scene = new Scene(mainWindow);
        this.stage.setTitle(CHATBOT_NAME);
        this.stage.setScene(scene);
        mainWindow.setParser(parser);
        greet();
        this.stage.show();
    }

    /**
     * Ends the program by closing the window.
     */
    public void stop() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> Platform.exit());
        delay.play();
    }

    /**
     * Prints the bot's response to the main window.
     * 
     * @param response The bot's response.
     */
    public void printBotResponse(String... response) {
        String formattedResponse = String.join("\n", response);
        mainWindow.printBotResponse(formattedResponse);
    }

    /**
     * Prints a greeting message to the main window.
     */
    public void greet() {
        String greeting = "Hello! I'm " + CHATBOT_NAME;
        printBotResponse(greeting, "What can I do for you?");
    }

    /**
     * Prints a farewell message to the main window.
     */
    public void farewell() {
        printBotResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a help message to the main window.
     */
    public void printHelp() {
        printBotResponse("Here are the commands you can use:",
                "list - List all tasks",
                "todo <task> - Add a todo task",
                "deadline <task> /by <date> - Add a deadline task with date in yyyy-mm-dd format",
                "event <task> /from <date> /to <date> - Add an event task with dates in yyyy-mm-dd format",
                "mark <index> - Mark a task as done",
                "unmark <index> - Mark a task as not done yet",
                "delete <index> - Delete a task",
                "find <keyword> - Find tasks with the keyword",
                "postpone <index> <days> - Postpone a task by a number of days",
                "help - Show this help message",
                "bye - Exit the program");
    }

    /**
     * Prints the list of tasks to the main window.
     * 
     * @param list The list of tasks to print.
     */
    public void printListMessage(ArrayList<Task> list) {
        String[] listStrings = new String[list.size() + 1];
        listStrings[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= list.size(); i++) {
            listStrings[i] = i + "." + list.get(i - 1);
        }
        printBotResponse(listStrings);
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     * 
     * @param task The task that has been marked as done.
     */
    public void printDoneMessage(Task task) {
        printBotResponse("Nice! I've marked this task as done:", task.toString());
    }

    /**
     * Prints a message indicating that a task has been marked as not done yet.
     * 
     * @param task The task that has been marked as not done yet.
     */
    public void printUndoneMessage(Task task) {
        printBotResponse("Nice! I've marked this task as not done yet:", task.toString());
    }

    /**
     * Prints a message indicating that a task has been deleted.
     * 
     * @param task The task that has been deleted.
     * @param size The number of tasks remaining in the list.
     */
    public void printDeleteMessage(Task task, int size) {
        printBotResponse(
                "Noted. I've removed this task:",
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    /**
     * Prints a message indicating that a task has been added.
     * 
     * @param task The task that has been added.
     * @param size The number of tasks in the list after adding the task.
     */
    public void printTodoMessage(Task task, int size) {
        printBotResponse(
                ADD_TASK_MESSAGE,
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    /**
     * Prints a message indicating that a deadline task has been added.
     * 
     * @param task The deadline task that has been added.
     * @param size The number of tasks in the list after adding the task.
     */
    public void printDeadlineMessage(Task task, int size) {
        printBotResponse(
                ADD_TASK_MESSAGE,
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    /**
     * Prints a message indicating that an event task has been added.
     * 
     * @param task The event task that has been added.
     * @param size The number of tasks in the list after adding the task.
     */
    public void printEventMessage(Task task, int size) {
        printBotResponse(
                ADD_TASK_MESSAGE,
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    /**
     * Prints an error message for no task description.
     */
    public void printEmptyTaskErrorMessage() {
        printBotResponse("Don't expect me to remember nothing!");
    }

    /**
     * Prints an error message for no /by for deadline task.
     */
    public void printEmptyByErrorMessage() {
        printBotResponse("BY WHEN??!!");
    }

    /**
     * Prints an error message for no /from or /to for event task.
     */
    public void printInvalidEventDateErrorMessage() {
        printBotResponse("Give me a valid from and to!");
    }

    /**
     * Prints an error message for invalid date format for deadline or event task.
     */
    public void printInvalidDateFormatErrorMessage() {
        printBotResponse(
                "Invalid date format! Please use yyyy-mm-dd format." +
                        " Ensure you have provided the correct date.");
    }

    /**
     * Prints an error message for invalid index.
     */
    public void printInvalidIndexErrorMessage() {
        printBotResponse("Give me a valid index!");
    }

    /**
     * Prints an error message using reserved character.
     */
    public void printReservedCharacterErrorMessage() {
        printBotResponse("| is a special character and cannot be used.");
    }

    /**
     * Prints an error message for invalid command.
     */
    public void printInvalidCommandErrorMessage() {
        printBotResponse("Give me a valid command!");
    }

    /**
     * Prints an error message for postpone command with wrong task type.
     */
    public void printPostponeErrorMessage() {
        printBotResponse("Task given can't be postponed.");
    }

    /**
     * Prints a list of tasks that match the keyword.
     */
    public void printFindMessage(ArrayList<Task> list) {
        String[] listStrings = new String[list.size() + 1];
        listStrings[0] = "Here are the matching tasks in your list:";
        for (int i = 1; i <= list.size(); i++) {
            listStrings[i] = i + "." + list.get(i - 1);
        }
        printBotResponse(listStrings);
    }

    /**
     * Prints a message indicating that a task has been postponed.
     * 
     * @param task The task that has been postponed.
     * @param days The number of days the task has been postponed by.
     */
    public void printPostponeMessage(Task task, int days) {
        printBotResponse("Got it. I've postponed this task by " + days + " days:", task.toString());
    }
}
