package jade.command;

import jade.exception.JadeException;
import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;
import jade.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to sort tasks.
 */
public class SortCommand extends Command {
    private final String command;

    /**
     * Constructs a SortCommand object with the specified TaskManager and sort type.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param command The command string containing task details.
     */
    public SortCommand(TaskManager taskManager, String command) {
        super(taskManager);
        this.command = command;
    }
    @Override
    public String runForGui() {
        try {
            return displaySortedTaskListMessage(FOR_GUI);
        } catch (JadeException e) {
            return e.getMessage();
        }
    }

    @Override
    public String run() {
        try {
            return displaySortedTaskListMessage(FOR_TEXT_UI);
        } catch (JadeException e) {
            return Ui.formatTextUiMessage(e.getMessage());
        }
    }

    private String displaySortedTaskListMessage(boolean forGui) throws JadeException {
        try {
            String sortType = command.split(" by ")[1].trim();
            sortTasksBySortType(sortType);
            ArrayList<Task> sortedTasks = taskManager.getAllTasks();
            StringBuilder message = new StringBuilder("Here are the sorted task(s) in your list:");
            for (int i = 0; i < sortedTasks.size(); i++) {
                message.append("\n");
                indentIfNotGui(forGui, message);
                message.append(i + 1).append(". ");

                Task task = sortedTasks.get(i);
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
            message.append("Please specify a valid sort type in the format:\n");
            indentIfNotGui(forGui, message);
            message.append("  sort by <type>");

            throw new JadeException(message.toString());
        }
    }

    private void sortTasksBySortType(String sortType) throws JadeException {
        switch (sortType) {
        case "alphabet":
            taskManager.sortTasksAlphabetically();
            break;
        case "task type":
            taskManager.sortTasksByTaskType();
            break;
        case "deadline":
            taskManager.sortTasksByDeadline();
            break;
        case "event":
            taskManager.sortTasksByEvent();
            break;
        default:
            throw new JadeException("Invalid sort type! Try sort by alphabet or task type.");
        }
    }
}
