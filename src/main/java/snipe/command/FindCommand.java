package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code FindCommand} class represents a command to find tasks
 * in the task list that contain a specific keyword.
 */
public class FindCommand extends Command{
    /** The keyword to search for in the tasks. */
    private final String keyWord;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyWord The keyword to search for in the tasks.
     */
    public FindCommand(String keyWord) {
        // Assert that the keyword is not null and not empty
        assert keyWord != null && !keyWord.trim().isEmpty() : "Keyword must not be null or empty";
        this.keyWord = keyWord;
    }

    /**
     * Executes the find command by searching for tasks in the task list that contain the specified keyword
     * and returns a list of matching tasks as a formatted string.
     *
     * @param tasks          The {@link TaskList} to search within for matching tasks.
     * @param ui             The {@link Ui} instance used to interact with the user.
     * @param storage        The {@link Storage} instance, used to save or load task data.
     * @param archiveTasks   The {@link TaskList}, not used in this command.
     * @param archiveStorage The {@link Storage} instance, not used in this command.
     * @return A formatted string listing all tasks in the task list that contain the specified keyword.
     * @throws SnipeException If an application-specific error occurs during execution.
     * @throws IOException    If an I/O error occurs while accessing storage.
     */
    @Override
    public String getResponse(
            TaskList tasks,
            Ui ui,
            Storage storage,
            TaskList archiveTasks,
            Storage archiveStorage
    ) throws SnipeException, IOException {
        // Assert that the TaskList is not null
        assert tasks != null : "TaskList should not be null";

        ArrayList<Task> filteredTasks = tasks.findTasks(this.keyWord);
        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            // Assert that each task in filteredTasks is not null
            assert filteredTasks.get(i) != null : "Task in filtered list should not be null";

            String item = String.format("%d. %s\n", i + 1, filteredTasks.get(i).toString());
            message.append(item);
        }
        String resultMessage = message.toString().stripTrailing();
        return resultMessage;
    }

}
