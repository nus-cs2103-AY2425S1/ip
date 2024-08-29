package reminderebot.commands;

import reminderebot.TaskList;
import reminderebot.Ui;
import reminderebot.Storage;
import reminderebot.ReminderebotException;

/**
 * The ListCommand class represents a command to list all tasks in tasklist.
 */
public class ListCommand extends Command {
    /**
     * Instantiate a ListCommand
     */
    public ListCommand() {}

    /**
     * Executes the List command.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.printTasks();
    }

    /**
     * List command does not exit
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
