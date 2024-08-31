package phenex.ui;

import phenex.exception.PhenexException;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.task.TaskWithDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class Ui {

    private final String LINE = "\t____________________________________________________________\n";
    private final String LOGO = "  _____    _    _   ______   _   _   ______  __   __\n"
            + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
            + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V /\n"
            + " | |      | |  | | | |____  | |\\  | | |____   / . \\\n"
            + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";
    private final String MSG_GREET = "Hello! I'm\n"
            + LOGO
            + "Your favourite solid gold mobile suit!\n"
            + LINE
            + "\tWhat can I do for you?\n"
            + LINE;
    private final String MSG_FAREWELL = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + LINE;

    public void greet() {
        System.out.print(MSG_GREET);
    }

    public void sayFarewell() {
        System.out.println(MSG_FAREWELL);
    }

    public void printLine() {
        System.out.print(LINE);
    }

    /**
     * Prints all tasks in the taskList
     *
     * @param taskList
     */
    public void printTaskList(TaskList taskList) {
        int size = taskList.getTasks().size();
        if (size == 0) {
            System.out.println("\t No scheduled missions. Rest up for the next battle, soldier!");
            return;
        }

        System.out.println("\t Outstanding missions:");
        for (int i = 0; i < size; i++) {
            String row = "\t "
                    + (i + 1)
                    + "."
                    + taskList.getTaskByIdx(i);
            System.out.println(row);
        }
    }

    /**
     * Prints invalid input message.
     *
     */
    public static void printInvalidInputMessage() {
        System.out.println("\tUnknown input!");
    }

    /**
     * Prints exception message.
     *
     * @param e, Exception which contains the message to print.
     */
    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the relevant messages when a task is marked as complete.
     *
     * @param taskMarked, task which was marked as complete
     */
    public static void printTaskMarkedCompleteMessage(Task taskMarked) {
        System.out.println("\t Mission marked as complete. Good job, soldier!");
        System.out.println("\t\t" + taskMarked);
    }

    /**
     * Prints the relevant message when a task is marked as incomplete.
     *
     * @param taskMarked, task which was marked as incomplete.
     */
    public static void printTaskMarkedIncompleteMessage(Task taskMarked) {
        System.out.println("\t Mission marked as incomplete.");
        System.out.println("\t\t" + taskMarked);
    }

    /**
     * Prints the relevant messages when a task was deleted.
     *
     * @param taskDeleted, task which was deleted
     * @param taskListSize, current size of the task list
     */
    public void printTaskDeletedMessage(Task taskDeleted, int taskListSize) {
        System.out.println("\t OK. Mission aborted, retreat!");
        System.out.println("\t  " + taskDeleted);
        System.out.println("\t " + taskListSize + " missions remaining. Destroy the enemy!");
    }

    /**
     * Prints relevant message when a task is added.
     *
     * @param taskAdded, task which was added
     * @param taskListSize, current size of task list
     */
    public void printTaskAddedMessage(Task taskAdded, int taskListSize) {
        System.out.println("\t Mission " + taskAdded.getName() + " added:");
        System.out.println("\t   " + taskAdded);
        System.out.println("\t Total upcoming missions: " + taskListSize);
    }

    /**
     * Prints relevant message when memory initialised.
     *
     */
    public static void printMemoryInitialisedMessage() {
        System.out.println("\tMemory successful initialised.");
    }

    /**
     * Prints relevant message when memory fails to initialise.
     *
     */
    public static void printMemoryInitialisingFailureMessage() {
        System.out.println("\tCorrupted memory detected. Aborting sequence!");
    }
}
