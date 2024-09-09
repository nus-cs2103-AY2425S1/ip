package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor to initalise DeadlineCommand
     *
     * @param input
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "DeadlineCommand";
    }
}
