package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;

/**
 * Represents a command that handles the exit operation in BeeBoo.
 * This command will trigger the necessary procedures to terminate
 * the chatbot, including saving the current task list and displaying
 * a farewell message to the user.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command. This method will close the user interface
     * and display a farewell message. Any necessary cleanup procedures,
     * such as saving data, should also be handled here.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The Ui component that handles user interaction.
     * @param storage The Storage component for saving/loading data.
     * @throws InvalidDateException    If an invalid date is encountered during execution.
     * @throws NoDescriptionException  If a task is missing a description during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException {
        ui.close();
        ui.byeMessageMessage();
    }

    /**
     * Determines if this command is an exit command.
     *
     * @return {@code true} if this is an exit command, {@code false} otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
