package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents a delete command to delete task from list
 */
public class DeleteCommand extends Command{

    // string representing task number to be deleted
    private String[] parsed;

    public DeleteCommand(String[] parsed) {
        this.parsed = parsed;
    }

    /**
     * Executes delete command
     *
     * @param list list task is to be deleted from
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @throws TalkerException if unable to delete task
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.deleteTask(parsed, ui);
    }

}
