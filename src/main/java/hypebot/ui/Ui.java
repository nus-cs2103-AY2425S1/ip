package hypebot.ui;

import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static hypebot.common.Messages.MESSAGE_ADDED_TASK;
import static hypebot.common.Messages.MESSAGE_DELETED_TASK;
import static hypebot.common.Messages.ERROR_INTRO;
import static hypebot.common.Messages.MESSAGE_EXIT;
import static hypebot.common.Messages.MESSAGE_GREET_INTRO;
import static hypebot.common.Messages.MESSAGE_GREET_OUTRO;
import static hypebot.common.Messages.MESSAGE_HAPPENING;
import static hypebot.common.Messages.MESSAGE_HELP;
import static hypebot.common.Messages.MESSAGE_LIST;
import static hypebot.common.Messages.MESSAGE_LOAD_TASKLIST;
import static hypebot.common.Messages.LOGO;
import static hypebot.common.Messages.MESSAGE_MARKED_TASK;
import static hypebot.common.Messages.MESSAGE_TASKS_LEFT_INTRO;
import static hypebot.common.Messages.MESSAGE_TASKS_LEFT_OUTRO;
import static hypebot.common.Messages.MESSAGE_SAVE_TASKLIST;
import static hypebot.common.Messages.MESSAGE_UNKNOWN_INTRO;
import static hypebot.common.Messages.MESSAGE_UNKNOWN_OUTRO;
import static hypebot.common.Messages.MESSAGE_UNMARKED_TASK;

/**
 * Represents the user interface that user interacts with HypeBot.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Ui {
    private final String DIVIDER_LINE = "____________________________________________________________________"
            + "____________________________________________________________\n";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a new Ui object that initiates the scanner for user input and output interface.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Returns message formatted with buffer lines.
     *
     * @param message String to be reformatted.
     * @return Reformatted string with buffer lines.
     */
    private String addDividerLine(String message) {
        return DIVIDER_LINE + message + DIVIDER_LINE;
    }

    /**
     * Returns error message formatted with buffer lines.
     *
     * @param message Error message to be reformatted.
     * @return Reformatted string with buffer lines.
     */
    private String addDividerLineError(String message) {
        return DIVIDER_LINE + ERROR_INTRO + message + DIVIDER_LINE;
    }

    /**
     * Outputs the divider line to the user interface.
     */
    public void showDividerLine() {
        out.println(DIVIDER_LINE);
    }

    /**
     * Outputs message shown when loading tasks from tasks.txt.
     */
    public void showLoadingTasks() {
        out.println(addDividerLine(MESSAGE_LOAD_TASKLIST));
    }

    /**
     * Outputs message shown when saving tasks to tasks.txt.
     */
    public void showSavingTasks() {
        out.println(addDividerLine(MESSAGE_SAVE_TASKLIST));
    }

    /**
     * Outputs help message when user enters 'help' command.
     */
    public void showHelpMessage() {
        out.println(addDividerLine(MESSAGE_HELP));
    }

    /**
     * Takes in a Tasklist storing the user's current tasks and outputs them to user interface.
     *
     * @param tasks Tasks to output to user interface.
     */
    public void showListingTasks(Tasklist tasks) {
        out.println(addDividerLine(MESSAGE_LIST + tasks.toString()));
    }

    /**
     * Takes in a Task and Tasklist object and outputs that the Task was added to the Tasklist.
     * Also shows number of tasks left in the Tasklist.
     *
     * @param addedTask Task that was added to Tasklist.
     * @param tasks Tasklist with the added Task.
     */
    public void showAddedTask(Task addedTask, Tasklist tasks) {
        out.println(addDividerLine(MESSAGE_ADDED_TASK + addedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO));
    }

    /**
     * Takes in a Task and Tasklist object and outputs that the Task was removed
     * from the Tasklist. Also shows the number of tasks left in the Tasklist.
     *
     * @param removedTask Task that was deleted from Tasklist.
     * @param tasks Tasklist with corresponding Task removed.
     */
    public void showDeletedTask(Task removedTask, Tasklist tasks) {
        out.println(addDividerLine(MESSAGE_DELETED_TASK + removedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO));
    }

    /**
     * Takes in a Task object and outputs that the Task was marked complete.
     *
     * @param taskToMark Task marked complete.
     */
    public void showMarkedTask(Task taskToMark) {
        out.println(addDividerLine(MESSAGE_MARKED_TASK + taskToMark + "\n"));
    }

    /**
     * Takes in a Task object and outputs that the Task was marked incomplete.
     *
     * @param taskToUnmark Task marked incomplete.
     */
    public void showUnmarkedTask(Task taskToUnmark) {
        out.println(addDividerLine(MESSAGE_UNMARKED_TASK + taskToUnmark + "\n"));
    }

    /**
     * Takes in a LocalDate and Tasklist object and outputs the date in MMM d yyyy
     * format, as well as tasks occurring on the given LocalDate.
     *
     * @param searchDate Date (shown to user in MMM d yyyy format) user entered to search.
     * @param tasksHappeningOnDate Tasklist containing tasks occurring on corresponding date.
     */
    public void showTasksHappeningOnDate(LocalDate searchDate, Tasklist tasksHappeningOnDate) {
        out.println(addDividerLine(MESSAGE_HAPPENING + searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "!\n" + tasksHappeningOnDate.toString()));
    }

    /**
     * Takes in the unknown keyword entered by the user and outputs the unknown command message.
     *
     * @param command User-entered String that does not correspond to any existing commands.
     */
    public void showUnknownCommand(String command) {
        out.println(addDividerLineError(MESSAGE_UNKNOWN_INTRO + command + MESSAGE_UNKNOWN_OUTRO));
    }

    /**
     * Outputs the greeting message and logo to the user interface.
     */
    public void showGreeting() {
        out.println(addDividerLine(MESSAGE_GREET_INTRO + LOGO + MESSAGE_GREET_OUTRO));
    }

    /**
     * Outputs the exit message to the user interface.
     */
    public void showExit() {
        in.close();
        out.println(addDividerLine(MESSAGE_EXIT));
    }

    /**
     * Takes in an error message and outputs it to the user interface.
     *
     * @param errorMessage Error message to be outputted.
     */
    public void showError(String errorMessage) {
        out.println(addDividerLineError(errorMessage));
    }

    /**
     * Uses the Scanner to read user input to return the full command line entered by user.
     * In the case that the Scanner cannot detect the line, automatically assigns "start" command.
     *
     * @return String user input to be sent to Parser.
     */
    public String readCommand() {
        String userEnteredCommand = "start";
        if (in.hasNextLine()) {
            userEnteredCommand = in.nextLine();
        }
        return userEnteredCommand;
    }
}
