package monobot.command;

import monobot.exception.MonoBotException;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents list command to list out all tasks in list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes list command.
     *
     * @param tasks list of tasks to print.
     * @param ui ui object to handle output to user.
     * @param storage storage object to read/write file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }
}