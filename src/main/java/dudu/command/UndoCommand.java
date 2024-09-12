package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents an undo command
 */
public class UndoCommand extends Command {
    private Command command;

    /**
     * Constructs an undo command with no command to execute
     */
    public UndoCommand() {
        this.command = null;
    }

    /**
     * Constructs an undo command with a command to execute
     *
     * @param command Command to execute
     */
    public UndoCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the stored task. If there is no task, it informs user accordingly
     *
     * @param taskList The task list on which the command is executed
     * @param ui The user interface to interact with the user
     * @param storage The storage to save tasks
     * @return Successful undo response if task is execute, else empty undo command response
     * @throws IOException If there is an error during saving the task to storage
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        if (command == null) {
            return "There are no previous commands to undo";
        } else {
            String undoCommandResposne = command.execute(taskList, ui, storage);
            return "You have successfully undone the previous command!\n"
                    + undoCommandResposne;
        }
    }
}
