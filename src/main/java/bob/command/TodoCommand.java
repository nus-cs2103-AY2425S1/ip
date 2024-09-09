package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class TodoCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor to initalise TodoCommand
     *
     * @param input
     */
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "TodoCommand";
    }
}
