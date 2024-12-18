package barry.command;
import barry.util.TaskList;
import barry.util.Ui;
import barry.util.Storage;
import java.io.IOException;

/**
 * Represents an abstract command executed by the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param allTasks The list of all tasks on which the command is to be executed.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for saving tasks.
     * @throws IOException If there is an error during input or output operations.
     */
    public abstract void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException;

    /**
     * Indicates if the command is an exit command.
     * The default implementation returns false.
     *
     * @return true if the command is an exit command ie 'bye', false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
