package hypebot.ui;

import static hypebot.common.Messages.LOGO;
import static hypebot.common.Messages.MESSAGE_ADDED_TASK;
import static hypebot.common.Messages.MESSAGE_DELETED_ALL_TASKS;
import static hypebot.common.Messages.MESSAGE_DELETED_TASK;
import static hypebot.common.Messages.MESSAGE_EXIT;
import static hypebot.common.Messages.MESSAGE_FIND_INTRO;
import static hypebot.common.Messages.MESSAGE_GREET_INTRO;
import static hypebot.common.Messages.MESSAGE_GREET_OUTRO;
import static hypebot.common.Messages.MESSAGE_HAPPENING;
import static hypebot.common.Messages.MESSAGE_HELP;
import static hypebot.common.Messages.MESSAGE_LIST;
import static hypebot.common.Messages.MESSAGE_LOADING_TASKLIST;
import static hypebot.common.Messages.MESSAGE_MARKED_TASK;
import static hypebot.common.Messages.MESSAGE_SAVING_TASKLIST;
import static hypebot.common.Messages.MESSAGE_TASKS_LEFT_INTRO;
import static hypebot.common.Messages.MESSAGE_TASKS_LEFT_OUTRO;
import static hypebot.common.Messages.MESSAGE_UNKNOWN_INTRO;
import static hypebot.common.Messages.MESSAGE_UNKNOWN_OUTRO;
import static hypebot.common.Messages.MESSAGE_UNMARKED_TASK;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

/**
 * Represents the user interface that user interacts with HypeBot.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class UiCli {
    /**
     * Creates a new UiCli object that initiates the scanner for user input and output interface.
     */
    public UiCli() {
        
    }

    /**
     * Outputs message shown when loading tasks from tasks.txt.
     */
    public UiResponse showLoadingTasks() {
        return new UiResponse(MESSAGE_LOADING_TASKLIST);
    }

    /**
     * Outputs message shown when saving tasks to tasks.txt.
     */
    public UiResponse showSavingTasks() {
        return new UiResponse(MESSAGE_SAVING_TASKLIST);
    }

    /**
     * Outputs help message when user enters 'help' command.
     */
    public UiResponse showHelpMessage() {
        return new UiResponse(MESSAGE_HELP);
    }

    /**
     * Takes in a Tasklist storing the user's current tasks and outputs them to user interface.
     *
     * @param tasks Tasks to output to user interface.
     */
    public UiResponse showListingTasks(Tasklist tasks) {
        return new UiResponse(MESSAGE_LIST + tasks.toString());
    }

    /**
     * Takes in a Task and Tasklist object and outputs that the Task was added to the Tasklist.
     * Also shows number of tasks left in the Tasklist.
     *
     * @param addedTask Task that was added to Tasklist.
     * @param tasks Tasklist with the added Task.
     */
    public UiResponse showAddedTask(Task addedTask, Tasklist tasks) {
        return new UiResponse(MESSAGE_ADDED_TASK + addedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO);
    }

    /**
     * Takes in a Task and Tasklist object and outputs that the Task was removed
     * from the Tasklist. Also shows the number of tasks left in the Tasklist.
     *
     * @param removedTask Task that was deleted from Tasklist.
     * @param tasks Tasklist with corresponding Task removed.
     */
    public UiResponse showDeletedTask(Task removedTask, Tasklist tasks) {
        return new UiResponse(MESSAGE_DELETED_TASK + removedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO);
    }

    public UiResponse showDeletedAllTasks() {
        return new UiResponse(MESSAGE_DELETED_ALL_TASKS);
    }

    /**
     * Takes in a Task object and outputs that the Task was marked complete.
     *
     * @param taskToMark Task marked complete.
     */
    public UiResponse showMarkedTask(Task taskToMark) {
        return new UiResponse(MESSAGE_MARKED_TASK + taskToMark + "\n");
    }

    /**
     * Takes in a Task object and outputs that the Task was marked incomplete.
     *
     * @param taskToUnmark Task marked incomplete.
     */
    public UiResponse showUnmarkedTask(Task taskToUnmark) {
        return new UiResponse(MESSAGE_UNMARKED_TASK + taskToUnmark + "\n");
    }

    /**
     * Takes in a LocalDate and Tasklist object and outputs the date in MMM d yyyy
     * format, as well as tasks occurring on the given LocalDate.
     *
     * @param searchDate Date (shown to user in MMM d yyyy format) user entered to search.
     * @param tasksHappeningOnDate Tasklist containing tasks occurring on corresponding date.
     */
    public UiResponse showTasksHappeningOnDate(LocalDate searchDate, Tasklist tasksHappeningOnDate) {
        return new UiResponse(MESSAGE_HAPPENING + searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "!\n" + tasksHappeningOnDate.toString());
    }

    /**
     * Takes in a UiResponse search query from user input parsed by CommandParser sent to a FindCommand,
     * a Tasklist with the Tasks containing one or more of the keywords in the search query,
     * then outputs the Tasklist containing the specified keywords.
     *
     * @param searchQuery String search query containing keywords to find in Task name specified by user.
     * @param tasksWithSearchQuery Tasklist containing Tasks with keyword(s) in their names.
     */
    public UiResponse showTasksWithSearchQuery(String searchQuery, Tasklist tasksWithSearchQuery) {
        return new UiResponse(MESSAGE_FIND_INTRO + " '" + searchQuery + "': \n"
                + tasksWithSearchQuery.toString());
    }

    /**
     * Takes in the unknown keyword entered by the user and outputs the unknown command message.
     *
     * @param command User-entered String that does not correspond to any existing commands.
     */
    public UiResponse showUnknownCommand(String command) {
        return showError(MESSAGE_UNKNOWN_INTRO + command + MESSAGE_UNKNOWN_OUTRO);
    }

    /**
     * Outputs the greeting message and logo to the user interface.
     */
    public UiResponse showGreeting() {
        return new UiResponse(MESSAGE_GREET_INTRO + LOGO + MESSAGE_GREET_OUTRO);
    }

    /**
     * Outputs the exit message to the user interface.
     */
    public UiResponse showExit() {
        return new UiResponse(MESSAGE_EXIT);
    }

    /**
     * Takes in an error message and outputs it to the user interface.
     *
     * @param errorMessage Error message to be outputted.
     */
    public UiResponse showError(String errorMessage) {
        return new UiErrorResponse(errorMessage);
    }
}
