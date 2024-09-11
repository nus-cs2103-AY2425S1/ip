package jade.command;

import java.util.ArrayList;

import jade.exception.JadeException;
import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;
import jade.ui.Ui;

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

        assert taskManager != null : "TaskManager should not be null";
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        this.command = command;
    }

    @Override
    public String runForGui() {
        try {
            return displayFoundTaskListMessage(FOR_GUI);
        } catch (JadeException e) {
            return e.getMessage();
        }
    }

    @Override
    public String run() {
        try {
            return displayFoundTaskListMessage(FOR_TEXT_UI);
        } catch (JadeException e) {
            return Ui.formatTextUiMessage(e.getMessage());
        }
    }

    private String displayFoundTaskListMessage(boolean forGui) throws JadeException {
        try {
            String keyword = command.split(" ")[1].trim();
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
                message.append(i + 1).append(". ");

                Task task = matchingTasks.get(i);
                if (forGui && task instanceof Event) {
                    Event temp = (Event) task;
                    message.append(temp.toStringForGui());
                } else {
                    message.append(task);
                }
            }

            return displayMessage(forGui, message.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            StringBuilder message = new StringBuilder();
            message.append("Please specify a valid keyword in the format:\n");
            indentIfNotGui(forGui, message);
            message.append("  find <keyword>");

            throw new JadeException(message.toString());
        }
    }
}
