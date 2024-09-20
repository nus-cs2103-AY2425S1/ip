package bob.ui;

import java.util.Scanner;

import bob.parser.Parser;
import bob.task.TaskList;

/**
 * Class that handles interactions with the user.
 */
public class Ui {
    private Parser parser;

    /**
     * Prints lines above and below a given String input.
     *
     * @param text String input.
     */
    public static void printLines(String text) {
        String textToPrint = "\t____________________________________________________________\n"
                + "\t"
                + text
                + "\n"
                + "\t____________________________________________________________\n";
        System.out.println(textToPrint);
    }

    /**
     * Prints welcome message.
     */
    public static void showWelcome() {
        String welcome = "Hello! I'm Bob\n"
                + "\tWhat can I do for you?";
        Ui.printLines(welcome);
    }

    /**
     * Returns a string representation of Bob's welcome message.
     */
    public static String getWelcomeMessage() {
        String welcome = "Hello! I'm Bob\n"
                + "What can I do for you?";
        return welcome;
    }

    /**
     * Prints a goodBye message to user when program is terminated by user.
     */
    public static void showGoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon!";
        Ui.printLines(goodByeMessage);
    }

    /**
     * Prints a request for the user to key in a valid command.
     */
    public static void requestValidCommand() {
        String loadingError = "Please input a valid command\n"
                + "Valid Commands: list|mark|unmark|delete|event|deadline|todo|find|bye";
        Ui.printLines(loadingError);
    }

    /**
     * Prints the results found that match with the find function.
     * @param resultsFound
     */
    public static void showFindResults(String resultsFound) {
        Ui.printLines(resultsFound);
    }

    /**
     * Returns the String represented of the user's input.
     *
     * @return
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        return input;
    }

    public String showLine() {
        return "\t____________________________________________________________\n";
    }

    public void showList(String allRecords) {
        Ui.printLines(allRecords);
    }

    /**
     * Prints whether a task is marked or not.
     *
     * @param markedTaskString String representation of confirmation message when task is marked.
     */
    public static void showMarkedTask(String markedTaskString) {
        Ui.printLines(markedTaskString);
    }

    /**
     * Prints the deleted Task String.
     */
    public static void showDeletedTask(String deletedTaskString) {
        Ui.printLines(deletedTaskString);
    }

    /**
     * Prints the Task with updated Tag String.
     */
    public static void showTaggedTask(String taggedTaskString) {
        Ui.printLines(taggedTaskString);
    }

    /**
     * Prints confirmation after user adds a task.
     *
     * @param taskList
     */
    public static void showAddedTaskConfirmation(TaskList taskList) {
        String addedTaskConfirmationString = taskList.getAddedTaskString();
        Ui.printLines(addedTaskConfirmationString);
    }

    /**
     * Prints all records for user.
     *
     * @param allrecords
     */
    public static void showAllRecordsString(String allrecords) {
        Ui.printLines(allrecords);
    }

}
