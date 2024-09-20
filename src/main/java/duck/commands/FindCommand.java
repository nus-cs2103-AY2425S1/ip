package duck.commands;

import duck.Parser;
import duck.TaskList;

public class FindCommand extends Command {
    private TaskList taskList;
    private Parser lineBuffer;

    public FindCommand(TaskList taskList, Parser lineBuffer) {
        this.taskList = taskList;
        this.lineBuffer = lineBuffer;
    }

    @Override
    public String executeCommand() {
        String pattern = lineBuffer.getRemainingLine();
        TaskList filteredTasks = taskList.filterTasksByPattern(pattern);
        return "Here are the tasks in your list:\n"
                + filteredTasks.toString();
    }
}
