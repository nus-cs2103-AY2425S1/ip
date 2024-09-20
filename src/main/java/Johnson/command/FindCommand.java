package Johnson.command;

import Johnson.task.Task;
import Johnson.utils.Utilities;

import java.util.ArrayList;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    /**
     * The command word that identifies a FindCommand instance.
     */
    public static final String COMMAND_WORD = "find";

    /**
     * The message that is displayed when a FindCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Got a few tangos on the radar, Chief:\n";

    /**
     * The message that is displayed when no tasks are found.
     */
    private static final String COMMAND_MSG_EMPTY = "Don't know anyone by that name, Chief.";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeCommand() {
        ArrayList<Task> foundTasks = taskList.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            Utilities.OutlineMessage(COMMAND_MSG_EMPTY);
            return COMMAND_MSG_EMPTY;
        }
        String message = COMMAND_MSG;
        for (int i = 0; i < foundTasks.size(); i++) {
            message = message + (i + 1) + ":" + foundTasks.get(i) + "\n";
        }
        Utilities.OutlineMessage(message);
        return message;
    }
}
