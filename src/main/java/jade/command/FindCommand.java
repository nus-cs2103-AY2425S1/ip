package jade.command;

import java.util.ArrayList;

import jade.task.Task;
import jade.task.TaskManager;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String command;

    /**
     * Constructs a FindCommand object with the specified TaskManager and command.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param command The command string containing task details.
     */
    public FindCommand(TaskManager taskManager, String command) {
        super(taskManager);
        this.command = command;
    }

    @Override
    public String runForGui() {
        return displayFoundTaskListMessage(FOR_GUI);
    }

    @Override
    public String run() {
        return displayFoundTaskListMessage(FOR_TEXT_UI);
    }

    private String displayFoundTaskListMessage(boolean forGui) {
        String keyword = command.substring(5).trim();
        ArrayList<Task> matchingTasks = taskManager.getMatchingTasks(keyword);
        StringBuilder message = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            message.append("No matching tasks found.");
            return displayMessage(forGui, message.toString());
        }

        message.append("Here are the matching task(s) in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            message.append("\n");
            indentIfNotGui(forGui, message);
            message.append(i + 1).append(".").append(matchingTasks.get(i));
        }

        return displayMessage(forGui, message.toString());
    }
}
