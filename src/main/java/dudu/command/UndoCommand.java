package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents an undo command.
 */
public class UndoCommand extends Command {
    private Command command;

    /**
     * Constructs an UndoCommand with no command to execute.
     */
    public UndoCommand() {
        this.command = null;
    }

    /**
     * Constructs an UndoCommand with a command to execute
     *
     * @param command Command to execute.
     */
    public UndoCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the stored command if it exists.
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     * @return Message notifying user of stored command execution.
     * @throws IOException If there is an error during rewriting the local file in storage.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        if (command == null) {
            return "There are no previous commands to undo";
        } else {
            String undoCommandResponse = command.execute(taskList, ui, storage);
            return "You have successfully undone the previous command!\n"
                    + undoCommandResponse;
        }
    }
}
