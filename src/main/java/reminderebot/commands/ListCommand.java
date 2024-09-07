package reminderebot.commands;

import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;

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
     * @return String representing List command
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return tasklist.printTasks();
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
