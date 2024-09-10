package jade.command;

import static jade.ui.Ui.INDENT;

import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object with the specified TaskManager.
     *
     * @param taskManager The TaskManager to manage the tasks.
     */
    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String run() {
        StringBuilder message = new StringBuilder();
        message.append(INDENT).append("Here are the tasks in your list:");
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            message.append("\n").append(INDENT).append(i + 1).append(". ").append(taskManager.getTask(i));
        }
        return displayMessage(message.toString());
    }

    @Override
    public String runForGui() {
        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks in your list:");
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            message.append("\n").append(i + 1).append(". ");
            Task task = taskManager.getTask(i);
            if (task instanceof Event) {
                Event temp = (Event) task;
                message.append(temp.toStringForGui());
            } else {
                message.append(task);
            }
        }
        return message.toString();
    }
}
