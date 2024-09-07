package nixy.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Consumer;

import nixy.exceptions.NixyException;
import nixy.task.Task;
import nixy.task.TaskList;

/**
 * Ui class is responsible for handling user interface.
 * It provides methods to read input from the user and display messages to the user.
 */
public class Ui {
    private Consumer<String[]> display;

    public Ui() {
        this.display = PrintUtility::wrapPrintWithHorizontalLines;
    }

    private void displayMessage(String... lines) {
        display.accept(lines);
    }

    /**
     * Read input from the user via command line interface.
     */
    public static String readCliInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public void setDisplay(Consumer<String[]> display) {
        this.display = display;
    }

    public void showWelcome() {
        displayMessage("Hello! I'm Nixy", "What can I do for you?");
    }

    public void showGoodbye() {
        displayMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Display the list of tasks to the user.
     * @param tasks The list of tasks to display.
     */
    public void showList(TaskList tasks) {
        displayList(tasks, "Here are the tasks in your list:");
    }

    /**
     * Displays the list of tasks that find retrieved.
     *
     * @param tasks The list of found tasks.
     */
    public void showMatchingList(TaskList tasks) {
        displayList(tasks, "Here are the matching tasks in your list:");
    }

    /**
     * Helper method to display a list of tasks with a message.
     * The list is displayed with a number prefix.
     *
     * @param tasks The list of tasks to display.
     * @param message The message to display before the list.
     */
    private void displayList(TaskList tasks, String message) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(message);
        Iterator<Task> tasksIterator = tasks.getTasksIterator();
        for (int i = 0; tasksIterator.hasNext(); i++) {
            Task task = tasksIterator.next();
            lines.add(String.format("%d. %s", i + 1, task));
        }
        String printMessage = String.join("\n" + "    ", lines);
        displayMessage(printMessage.trim());
    }

    /**
     * Show the message that the task has been marked as done.
     * @param taskString The string represenation of task that was marked as done.
     */
    public void showMarkedAsDone(String taskString) {
        displayMessage("Nice! I've marked this task as done:",
            "  " + taskString);
    }

    /**
     * Show the message that the task has been marked as not done.
     * @param taskString The string represenation of task that was marked as not done.
     */
    public void showMarkedAsUndone(String taskString) {
        displayMessage("OK, I've marked this task as not done yet:",
            "  " + taskString);
    }

    /**
     * Show the message that the task has been deleted.
     * @param taskString The string represenation of task that was deleted.
     * @param taskCount The number of tasks remaining in the list.
     */
    public void showDeletedTask(String taskString, int taskCount) {
        displayMessage("Noted. I've removed this task:",
            "  " + taskString, String.format("Now you have %d tasks in the list.", taskCount));
    }

    /**
     * Display the NixyException message to the user.
     * @param e The exception to display.
     */
    public void showNixyException(NixyException e) {
        displayMessage(e.getMessage());
    }

    /**
     * Show the message that the task has been added.
     * @param task The task that was added.
     * @param taskCount The number of tasks in the list.
     */
    public void showAddedTask(Task task, int taskCount) {
        displayMessage("Got it. I've added this task:",
            "  " + task, String.format("Now you have %d tasks in the list.", taskCount));
    }

    /**
     * Alert user that command is not recognized.
     */
    public void showUnknownWarning() {
        displayMessage("HEY YOU ARE TYPING WEIRD THINGS! I don't understand.");
    }


}
