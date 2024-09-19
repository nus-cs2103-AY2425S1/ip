package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code ListCommand} class represents a command to display all tasks in the task list.
 * It formats the list of tasks and displays them to the user through the user interface.
 */
public class ListCommand extends Command {
    private boolean isArchiveListCommand;

    /**
     * Constructs a {@code ListCommand} with specification on if the list command
     * is to view archived list or the normal task list.
     *
     * @param isArchiveListCommand The index of the task to be archived (0-based index).
     */
    public ListCommand(boolean isArchiveListCommand) {
        this.isArchiveListCommand = isArchiveListCommand;
    }

    /**
     * Executes the list command by formatting all tasks in the task list into a numbered list format
     * and returning the formatted string to be displayed to the user.
     *
     * @param tasks          The {@link TaskList} containing all tasks to be displayed.
     * @param ui             The {@link Ui} instance used to display the task list to the user.
     * @param storage        The {@link Storage} instance, not used in this command.
     * @param archiveTasks   The {@link TaskList} containing archived task to be displayed.
     * @param archiveStorage The {@link Storage} instance, not used in this command.
     * @return A formatted string representing all tasks in the task list, in a numbered format.
     * @throws SnipeException If an application-specific error occurs during execution.
     * @throws IOException    If an I/O error occurs during execution (not applicable in this command).
     */
    @Override
    public String getResponse(
            TaskList tasks,
            Ui ui,
            Storage storage,
            TaskList archiveTasks,
            Storage archiveStorage
    ) throws SnipeException, IOException {
        // Assert that the task list is not null
        assert tasks != null : "TaskList should be initialized and not null";

        TaskList listToShow = isArchiveListCommand ? archiveTasks : tasks;

        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks in your " + (isArchiveListCommand ? "archived " : "") + "list:\n");

        for (int i = 0; i < listToShow.size(); i++) {
            // Assert that the task retrieved from the list is not null
            assert listToShow.getTask(i) != null : "Task in TaskList should not be null";

            String item = String.format("%d. %s\n", i + 1, listToShow.getTask(i).toString());
            message.append(item);
        }
        String resultMessage = message.toString().stripTrailing();
        return resultMessage;
    }

}
