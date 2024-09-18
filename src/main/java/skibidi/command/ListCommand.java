package skibidi.command;

import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public ListCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        return "Here are all your available goon quests:\n" + taskList.toString();
    }
}
