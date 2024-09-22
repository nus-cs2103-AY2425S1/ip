package duck.commands;

import duck.TaskList;
import duck.utils.Formatter;

/**
 * Class representing the "list" command.
 */
public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String executeCommand() {
        String response = "Here are the tasks in your list:\n"
                + Formatter.indentText(taskList.toString(), 4);
        return response;
    }
}
