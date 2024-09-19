package hypebot.ui.cli;

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

import hypebot.command.Command;
import hypebot.main.HypeBot;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

/**
 * Represents the {@code UiCli} that deals with all text that
 * user interacts with during a {@link HypeBot} interaction.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see UiResponse
 * @see UiErrorResponse
 */
public class UiCli {
    /**
     * Creates a new {@code UiCli}.
     */
    public UiCli() {}

    /**
     * Returns a new {@link UiResponse} telling the user that the {@link Tasklist}
     * saved on the user's local computer is being loaded.
     */
    public UiResponse showLoadingTasks() {
        return new UiResponse(MESSAGE_LOADING_TASKLIST);
    }

    /**
     * Returns a new {@link UiResponse} telling the user that the {@link Tasklist}
     * is being saved to the user's local computer is being loaded.
     */
    public UiResponse showSavingTasks() {
        return new UiResponse(MESSAGE_SAVING_TASKLIST);
    }

    /**
     * Returns a new {@link UiResponse} telling the user all the possible
     * {@link Command}s and how to format them to use the {@link HypeBot}.
     */
    public UiResponse showHelpMessage() {
        return new UiResponse(MESSAGE_HELP);
    }

    /**
     * Takes in a {@link Tasklist} storing the user's current tasks
     * and returns a new {@link UiResponse} that tells the user
     * the contents of the {@link Tasklist}.
     *
     * @param tasks {@link Tasklist} to list.
     */
    public UiResponse showListingTasks(Tasklist tasks) {
        return new UiResponse(MESSAGE_LIST + tasks.toString());
    }

    /**
     * Takes in a {@link Task} and {@link Tasklist} and returns a new
     * {@link UiResponse} telling the user the {@link Task} was added to
     * the {@link Tasklist}, as well as the number of {@link Task}s
     * left in the {@link Tasklist}.
     *
     * @param addedTask {@link Task} that was added to {@link Tasklist}.
     * @param tasks     {@link Tasklist} with the added {@link Task}.
     */
    public UiResponse showAddedTask(Task addedTask, Tasklist tasks) {
        return new UiResponse(MESSAGE_ADDED_TASK + addedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO);
    }

    /**
     * Takes in a {@link Task} and {@link Tasklist} and returns a new
     * {@link UiResponse} telling the user the {@link Task} was removed
     * from the {@link Tasklist}, as well as the number of {@link Task}s
     * left in the {@link Tasklist}.
     *
     * @param removedTask {@link Task} that was removed from {@link Tasklist}.
     * @param tasks       {@link Tasklist} after {@link Task} removed.
     */
    public UiResponse showDeletedTask(Task removedTask, Tasklist tasks) {
        return new UiResponse(MESSAGE_DELETED_TASK + removedTask
                + MESSAGE_TASKS_LEFT_INTRO + tasks.size() + MESSAGE_TASKS_LEFT_OUTRO);
    }

    /**
     * Returns a new {@link UiResponse} telling the user all {@link Task}s
     * were deleted from the {@link Tasklist}.
     */
    public UiResponse showDeletedAllTasks() {
        return new UiResponse(MESSAGE_DELETED_ALL_TASKS);
    }

    /**
     * Takes in a {@link Task} and returns a new {@link UiResponse} telling
     * the user the {@link Task} was marked complete.
     *
     * @param taskToMark {@link Task} marked complete.
     */
    public UiResponse showMarkedTask(Task taskToMark) {
        return new UiResponse(MESSAGE_MARKED_TASK + taskToMark + "\n");
    }

    /**
     * Takes in a {@link Task} and returns a new {@link UiResponse} telling
     * the user the {@link Task} was marked incomplete.
     *
     * @param taskToUnmark {@link Task} marked incomplete.
     */
    public UiResponse showUnmarkedTask(Task taskToUnmark) {
        return new UiResponse(MESSAGE_UNMARKED_TASK + taskToUnmark + "\n");
    }

    /**
     * Takes in a {@link LocalDate} and {@link Tasklist} of {@link Task}s
     * occurring on the given date, and returns a new {@link UiResponse}
     * showing the {@link Tasklist}.
     *
     * @param searchDate           {@link LocalDate} (shown to user in MMM d yyyy format)
     *                             user entered to search {@link Task}s happening.
     * @param tasksHappeningOnDate {@link Tasklist} containing {@link Task}s occurring on
     *                             corresponding search date.
     */
    public UiResponse showTasksHappeningOnDate(LocalDate searchDate, Tasklist tasksHappeningOnDate) {
        return new UiResponse(MESSAGE_HAPPENING + searchDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "!\n" + tasksHappeningOnDate.toString());
    }

    /**
     * Takes in a {@link String} search query and a {@link Tasklist} of {@link Task}s
     * whose name contains at least one of the keywords in the search query, and returns
     * a {@link UiResponse} showing the {@link Tasklist}.
     *
     * @param searchQuery          {@link String} search query containing keywords.
     * @param tasksWithSearchQuery {@link Tasklist} containing {@link Task}s with keyword(s)
     *                             in their names.
     */
    public UiResponse showTasksWithSearchQuery(String searchQuery, Tasklist tasksWithSearchQuery) {
        return new UiResponse(MESSAGE_FIND_INTRO + " '" + searchQuery + "': \n"
                + tasksWithSearchQuery.toString());
    }

    /**
     * Takes in an unknown {@link String} command keyword entered by the user
     * and returns a new {@link UiResponse} telling the user that the command is unrecognized.
     *
     * @param command User-entered {@link String} not matching any existing command keywords.
     */
    public UiResponse showUnknownCommand(String command) {
        return showError(MESSAGE_UNKNOWN_INTRO + command + MESSAGE_UNKNOWN_OUTRO);
    }

    /**
     * Returns a new {@link UiResponse} showing the greeting message and logo.
     */
    public UiResponse showGreeting() {
        return new UiResponse(MESSAGE_GREET_INTRO + LOGO + MESSAGE_GREET_OUTRO);
    }

    /**
     * Returns a new {@link UiResponse} showing the exit message.
     */
    public UiResponse showExit() {
        return new UiResponse(MESSAGE_EXIT);
    }

    /**
     * Takes in a {@link String} error message specifying what kind of error occurred
     * while interacting with the {@link HypeBot} and returns a new {@link UiErrorResponse}
     * showing the error message.
     *
     * @param errorMessage Error message to be outputted.
     */
    public UiErrorResponse showError(String errorMessage) {
        return new UiErrorResponse(errorMessage);
    }
}
