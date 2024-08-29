package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents list command to print all tasks in list
 */
public class ListCommand extends Command {
    /**
     * Executes list command
     *
     * @param list list of tasks to be printed
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @throws TalkerException if unable to read tasks to print
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.listTasks(ui);
    }

}
