package hypebot.ui;

import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static hypebot.common.Messages.*;

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

    private boolean shouldIgnore(String userEnteredCommand) {
        return userEnteredCommand.trim().isEmpty();
    }

    public void showDividerLine() {
        out.println(DIVIDER_LINE);
    }

    public void showLoadingTasks() {
        out.println(addDividerLine(LOADING_TASKLIST_MESSAGE));
    }

    public void showSavingTasks() {
        out.println(addDividerLine(SAVING_TASKLIST_MESSAGE));
    }

    public void showHelpMessage() {
        out.println(addDividerLine(HELP_MESSAGE));
    }

    public void showListingTasks(Tasklist tasks) {
        out.println(addDividerLine(LIST_INTRO + tasks.toString()));
    }

    public void showAddedTask(Task addedTask, Tasklist tasks) {
        out.println(addDividerLine(ADDED_TASK_MESSAGE + addedTask
                + NUMBER_OF_TASKS_LEFT_INTRO + tasks.size() + NUMBER_OF_TASKS_LEFT_OUTRO));
    }

    public void showDeletedTask(Task removedTask, Tasklist tasks) {
        out.println(addDividerLine(DELETED_TASK_MESSAGE + removedTask
                + NUMBER_OF_TASKS_LEFT_INTRO + tasks.size() + NUMBER_OF_TASKS_LEFT_OUTRO));
    }

    public void showMarkedTask(Task taskToMark) {
        out.println(addDividerLine(MARKED_TASK_MESSAGE + taskToMark + "\n"));
    }

    public void showUnmarkedTask(Task taskToUnmark) {
        out.println(addDividerLine(UNMARKED_TASK_MESSAGE + taskToUnmark + "\n"));
    }

    public void showTasksHappeningOnDate(LocalDate searchDate, Tasklist tasksHappeningOnDate) {
        out.println(addDividerLine(HAPPENING_INTRO + searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "!\n" + tasksHappeningOnDate.toString()));
    }

    public void showUnknownCommand(String command) {
        out.println(addDividerLineError(UNKNOWN_COMMAND_ERROR_INTRO + command + UNKNOWN_COMMAND_ERROR_OUTRO));
    }

    public void showGreeting() {
        out.println(addDividerLine(GREET_INTRO + LOGO + GREET_OUTRO));
    }

    public void showExit() {
        in.close();
        out.println(addDividerLine(EXIT_MESSAGE));
    }

    public void showError(String errorMessage) {
        out.println(addDividerLineError(errorMessage));
    }

    public String readCommand() {
        String userEnteredCommand = in.nextLine();
        while (shouldIgnore(userEnteredCommand)) {
            userEnteredCommand = in.nextLine();
        }
        return userEnteredCommand;
    }


}
