package Johnson.command;

import Johnson.task.Task;
import Johnson.utils.Utilities;

import java.util.ArrayList;

public class FindTagCommand extends Command {
    public static final String COMMAND_WORD = "findtag";

    private static final String COMMAND_MSG = "Chief, got some things on the radar with the tag:\n";

    private static final String EMPTY_MSG = "No tasks with this tag, Chief.\n";

    private final String tag;

    public FindTagCommand(String tag) {
        this.tag = tag;
    }

    @Override
    public String executeCommand() {
        assert Command.taskList != null : "Task list is null";
        ArrayList<Task> foundTasks = taskList.getTasksWithTag(tag);
        if (foundTasks.isEmpty()) {
            Utilities.OutlineMessage(EMPTY_MSG);
            return EMPTY_MSG;
        }
        String message = COMMAND_MSG;
        for (int i = 0; i < foundTasks.size(); i++) {
            message = message + (i + 1) + ":" + foundTasks.get(i) + "\n";
        }
        Utilities.OutlineMessage(message);
        return message;
    }
}
