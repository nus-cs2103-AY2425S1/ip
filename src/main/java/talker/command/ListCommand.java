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
     * @return String representing outcome of this event
     * @throws TalkerException if unable to read tasks to print
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.listTasks(ui);
    }

}
