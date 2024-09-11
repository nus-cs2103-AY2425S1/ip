package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents a command to list all the tasks in the list
 */
public class ListCommand extends Command {
    private String responseMessage;
    /**
     * Constructor for ListCommand
     *
     * @param command Description for the command
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Updates the message with the tasks in the current list
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        this.responseMessage = ui.showList();
    }

    public String getString() {
        assert this.responseMessage != null : "The execute() method must be called first";
        return this.responseMessage;
    }

    /**
     * Use to exit the program
     *
     * @return false and do not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
