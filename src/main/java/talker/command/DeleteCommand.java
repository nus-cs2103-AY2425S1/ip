package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents a delete command to delete task from list
 */
public class DeleteCommand extends Command {

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
     * @return String representing outcome of this event
     * @throws TalkerException if unable to delete task
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.deleteTask(parsed, ui);
    }

}
