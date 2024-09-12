package duck.commands;

import duck.Parser;
import duck.TaskList;
import duck.tasks.Task;
import duck.utils.Formatter;

public class MarkCommand extends RunOnTaskAtIndexCommand {
    public MarkCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer);
    }

    @Override
    public String executeOnTask(TaskList taskList, int taskLabel) {
        Task task = taskList.getItem(taskLabel);
        task.markAsDone();
        taskList.updateFileWithTaskList();

        String response = "Nice! I've marked this task as done:\n"
                + Formatter.indentText(task.toString(), 2);
        return response;
    }
}
