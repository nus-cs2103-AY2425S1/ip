package bob.ui;

import java.util.ArrayList;
import java.util.Scanner;

import bob.parser.Parser;
import bob.task.Task;

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
    public void showWelcome() {
        String welcome = "Hello! I'm Bob\n"
                + "\tWhat can I do for you?";
        Ui.printLines(welcome);
    }

    /**
     * Prints a goodBye message to user when program is terminated by user.
     */
    public static void showGoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon!";
        Ui.printLines(goodByeMessage);
    }

    /**
     * Prints error if Bob is unable to load file.
     */
    public void showLoadingError() {
        String loadingError = "Sorry! I'm unable to load the file\n";
        Ui.printLines(loadingError);
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
     * Prints the matching output of tasks that matches the user's keywords
     * @param matchingRecords Arraylist of all tasks with description that includes keyword.
     */
    public static void showSearchResults(ArrayList<Task> matchingRecords) {
        String toPrint = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (Task task: matchingRecords) {
            toPrint += "\t" + counter + "." + task.getTaskListItem() + "\n";
        }
        printLines(toPrint);
    }

    /**
     * Prints the results found that match with the find function.
     * @param resultsFound
     */
    public void showFindResults(String resultsFound) {
        Ui.printLines(resultsFound);
    }

    /**
     * Prints Error message when there is no successful records matching keyword.
     */
    public static void showEmptySearchResults() {
        System.out.println("No matching results are found:(\n"
                + "Please search for exact keyword");
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
     * @param currTask task that has been marked or unmarked.
     * @param isCompleted whether task has been completed.
     */
    public void showMarkedTask(Task currTask, boolean isCompleted) {
        if (isCompleted) {
            String finishedMarking = "OK, I've marked this task as done:\n\t"
                    + currTask.getTaskListItem();
            Ui.printLines(finishedMarking);
        } else {
            String finishedUnmarking = "OK, I've marked this task as not done yet:\n\t"
                    + currTask.getTaskListItem();
            Ui.printLines(finishedUnmarking);
        }
    }

    /**
     * Prints the deleted Task String.
     */
    public void showDeletedTask(String deletedTaskString) {
        Ui.printLines(deletedTaskString);
    }
}
