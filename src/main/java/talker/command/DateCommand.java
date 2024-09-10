package talker.command;

import talker.Storage;
import talker.TalkerException;
import talker.Ui;
import talker.task.TaskList;

/**
 * Represents date command to get all tasks that happen on certain date
 */
public class DateCommand extends Command {

    // string representing date
    private String input;

    public DateCommand(String input) {
        this.input = input;
    }

    /**
     * Executes date command
     *
     * @param list list of tasks to search for target date from
     * @param ui ui object to print output
     * @param storage storage object to read/write file
     * @return String representing outcome of this event
     * @throws TalkerException if unable to find tasks on certain date
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        return list.printTasksOn(input, ui);
    }

}
