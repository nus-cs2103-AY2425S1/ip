package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;
import orion.exceptions.OrionTaskListSaveException;

import java.util.List;

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
     * the storage, and handles any potential {@link OrionTaskListSaveException}.
     * Regardless of whether saving succeeds or fails, it prints a goodbye message.
     * </p>
     *
     * @param tasks  the {@link TaskList} containing the tasks to be saved
     * @param storage the {@link Storage} for saving the tasks
     * @param ui      the {@link Ui} for updating the user interface
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        // Saves tasks
        List<String> saveTaskDescriptions = tasks.getSavedTaskDescriptions();
        try {
            storage.saveTasks(saveTaskDescriptions);
            ui.printSaveTasks();
        } catch (OrionTaskListSaveException e) {
            ui.printSaveError();
        } finally {
            ui.printGoodbye();
        }
    }
}
