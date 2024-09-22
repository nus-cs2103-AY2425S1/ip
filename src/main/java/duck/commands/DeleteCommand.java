package duck.commands;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.DeleteUsageException;
import duck.tasks.Task;
import duck.utils.Formatter;

public class DeleteCommand extends RunOnTaskAtIndexCommand {
    public DeleteCommand(TaskList taskList, Parser lineBuffer) {
        super(taskList, lineBuffer, new DeleteUsageException());
    }

    @Override
    public String executeOnTask(TaskList taskList, int taskLabel) {
        // Delete
        Task task = taskList.getItem(taskLabel);
        taskList.removeItem(taskLabel);

        String response = "Noted. I've removed this task:\n"
                + Formatter.indentText(task.toString(), 2) + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.getTaskCount());
        return response;
    }
}
