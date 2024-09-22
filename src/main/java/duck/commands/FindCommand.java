package duck.commands;

import duck.Parser;
import duck.TaskList;

/**
 * Class representing the "find" command.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private Parser lineBuffer;

    /**
     * Constructor for FindCommand.
     *
     * @param taskList   List of tasks.
     * @param lineBuffer Buffer containing remaining command.
     */
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
