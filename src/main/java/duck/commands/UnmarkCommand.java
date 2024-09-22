package duck.commands;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.UnmarkUsageException;
import duck.tasks.Task;
import duck.utils.Formatter;

/**
 * Class representing the "unmark" command.
 */
public class UnmarkCommand extends RunOnTaskAtIndexCommand {
    public UnmarkCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, new UnmarkUsageException());
    }

    @Override
    public String executeOnTask(TaskList taskList, int taskLabel) {
        Task task = taskList.getItem(taskLabel);
        task.markAsNotDone();
        taskList.updateFileWithTaskList();

        String response = "OK, I've marked this task as not done yet:\n"
                + Formatter.indentText(task.toString(), 2);
        return response;
    }
}
