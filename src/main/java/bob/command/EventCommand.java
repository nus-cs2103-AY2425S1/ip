package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class EventCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor to initalise EventCommand
     *
     * @param input
     */
    public EventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "EventCommand";
    }
}
