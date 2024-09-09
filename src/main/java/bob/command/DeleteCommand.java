package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor to initalise DeleteCommand
     *
     * @param input
     */
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "DeleteCommand";
    }
}
