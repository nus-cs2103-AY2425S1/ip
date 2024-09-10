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

public class Ui {
    private final String DIVIDER_LINE = "____________________________________________________________________"
            + "____________________________________________________________\n";
    private final Scanner in;
    private final PrintStream out;

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

    public void showDividerLine() {
        out.println(DIVIDER_LINE);
    }

    public void showLoadingTasks() {
        out.println(addDividerLine(MESSAGE_LOAD_TASKLIST));
    }

    public void showSavingTasks() {
        out.println(addDividerLine(MESSAGE_SAVE_TASKLIST));
    }

    public void showHelpMessage() {
        out.println(addDividerLine(MESSAGE_HELP));
    }

    public void showListingTasks(Tasklist tasks) {
        out.println(addDividerLine(MESSAGE_LIST + tasks.toString()));
    }

    public void showAddedTask(Task addedTask, Tasklist tasks) {
        out.println(addDividerLine(MESSAGE_ADDED_TASK + addedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO));
    }

    public void showDeletedTask(Task removedTask, Tasklist tasks) {
        out.println(addDividerLine(MESSAGE_DELETED_TASK + removedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO));
    }

    public void showMarkedTask(Task taskToMark) {
        out.println(addDividerLine(MESSAGE_MARKED_TASK + taskToMark + "\n"));
    }

    public void showUnmarkedTask(Task taskToUnmark) {
        out.println(addDividerLine(MESSAGE_UNMARKED_TASK + taskToUnmark + "\n"));
    }

    public void showTasksHappeningOnDate(LocalDate searchDate, Tasklist tasksHappeningOnDate) {
        out.println(addDividerLine(MESSAGE_HAPPENING + searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "!\n" + tasksHappeningOnDate.toString()));
    }

    public void showUnknownCommand(String command) {
        out.println(addDividerLineError(MESSAGE_UNKNOWN_INTRO + command + MESSAGE_UNKNOWN_OUTRO));
    }

    public void showGreeting() {
        out.println(addDividerLine(MESSAGE_GREET_INTRO + LOGO + MESSAGE_GREET_OUTRO));
    }

    public void showExit() {
        in.close();
        out.println(addDividerLine(MESSAGE_EXIT));
    }

    public void showError(String errorMessage) {
        out.println(addDividerLineError(errorMessage));
    }

    public String readCommand() {
        String userEnteredCommand = "start";
        if (in.hasNextLine()) {
            userEnteredCommand = in.nextLine();
        }
        return userEnteredCommand;
    }


}
