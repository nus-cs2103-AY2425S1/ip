package orion.commands;

import java.util.List;

import orion.exceptions.OrionSaveTaskListException;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to exit the application.
 * <p>
 * This command saves the current state of the task list to the storage and
 * then terminates the application. It ensures that any tasks are saved before
 * exiting.
 * </p>
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand}. This command signals an exit,
     * so the {@code isExit} flag is set to {@code true}.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command by saving the tasks to storage and updating the
     * user interface.
     * <p>
     * This method retrieves the task descriptions, attempts to save them to
     * the storage, and handles any potential {@link OrionSaveTaskListException}.
     * Regardless of whether saving succeeds or fails, it prints a goodbye message.
     * </p>
     *
     * @param tasks  the {@link TaskList} containing the tasks to be saved
     * @param storage the {@link Storage} for saving the tasks
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        // Saves tasks
        List<String> saveTaskDescriptions = tasks.getSavedTaskDescriptions();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            storage.saveTasks(saveTaskDescriptions);
            stringBuilder.append("Your task list has been saved successfully!");
        } catch (OrionSaveTaskListException e) {
            stringBuilder.append("We had a problem saving your task list... Sorry about that!");
        } finally {
            stringBuilder.append("\nBye! Hope to see you again soon!");
        }
        return stringBuilder.toString();
    }
}
