package jade.command;

import jade.task.Task;
import jade.task.TaskManager;
import static jade.ui.Ui.INDENT;

import java.util.ArrayList;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String command;

    public FindCommand(TaskManager taskManager, String command) {
        super(taskManager);
        this.command = command;
    }

    @Override
    public String run() {
        String keyword = command.substring(5).trim();

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            Task task = taskManager.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        StringBuilder message = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            message.append(INDENT).append("No matching tasks found.");
        } else {
            message.append(INDENT).append("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append("\n").append(INDENT).append(i + 1).append(".").append(matchingTasks.get(i));
            }
        }

        return displayMessage(message.toString());
    }

    @Override
    public String runForGUI() {
        String keyword = command.substring(5).trim();

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            Task task = taskManager.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        StringBuilder message = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            message.append("No matching tasks found.");
        } else {
            message.append("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append("\n").append(i + 1).append(".").append(matchingTasks.get(i));
            }
        }

        return message.toString();
    }
}
