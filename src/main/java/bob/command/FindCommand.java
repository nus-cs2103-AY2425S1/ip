package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class FindCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor to initalise FindCommand
     *
     * @param input
     */
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "FindCommand";
    }
}
