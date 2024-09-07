package lawrence.command;

import java.io.IOException;

import lawrence.database.TaskFileManager;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * Represents a general command that can be issued by the user.
 * <p>
 * The command is not processed until
 * {@link #execute(TaskList, TaskFileManager, UserInterface)}
 * is called.
 * </p>
 */
public abstract class Command {
    /**
     * Executes the specified user command.
     *
     * @param tasks a list of tasks the command may operate
     *              on
     * @param manager a {@link TaskFileManager} instance that
     *                the command may use when saving changes
     *                made
     * @param ui a {@link UserInterface} instance to display
     *           possible messages to the user
     */
    public abstract void execute(TaskList tasks, TaskFileManager manager, UserInterface ui);

    /**
     * Returns a boolean indicating whether the program should
     * continue running after this command. Defaults to true.
     *
     * @return a boolean indicating whether the program should
     *         continue running
     */
    public boolean shouldContinue() {
        return true;
    }

    /**
     * Saves the current tasks in memory to a text file.
     * <p>
     * This operation will overwrite the original text file entirely.
     * </p>
     *
     * @param tasks the list of tasks to be saved into the text file
     * @param manager the {@link TaskFileManager} instance that
     *                can write to the text file
     * @throws IOException if writing to the file is unsuccessful
     */
    protected void saveTasks(TaskList tasks, TaskFileManager manager) throws IOException {
        manager.saveTasksToFile(tasks.getTasks());
    }
}
