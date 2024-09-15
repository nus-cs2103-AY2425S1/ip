package nixy.command;

import nixy.task.TaskList;
import nixy.ui.Ui;

/**
 * Class representing the command to list all tasks.
 */
public class ListCommand implements Command {
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for ListCommand.
     *
     * @param ui The Ui object to interact with the user.
     * @param tasks The TaskList object to store tasks.
     */
    public ListCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Returns the type of command.
     *
     * @return CommandType.LIST
     */
    @Override
    public CommandType getType() {
        return CommandType.LIST;
    }

    /**
     * Executes the command to list all tasks.
     */
    @Override
    public void execute() {
        ui.showList(tasks);
    }
}
