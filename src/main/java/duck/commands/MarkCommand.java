package duck.commands;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.BeforeEarliestTimeException;
import duck.exceptions.MarkUsageException;
import duck.tasks.Task;
import duck.utils.Formatter;

public class MarkCommand extends RunOnTaskAtIndexCommand {
    public MarkCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, new MarkUsageException());
    }

    @Override
    public String executeOnTask(TaskList taskList, int taskLabel) {
        Task task = taskList.getItem(taskLabel);

        try {
            task.markAsDone();
        } catch (BeforeEarliestTimeException e) {
            return e.toString();
        }

        taskList.updateFileWithTaskList();

        String response = "Nice! I've marked this task as done:\n"
                + Formatter.indentText(task.toString(), 2);
        return response;
    }
}
