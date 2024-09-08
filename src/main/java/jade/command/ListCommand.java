package jade.command;

import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;

import static jade.ui.Ui.INDENT;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

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
    public String runForGUI() {
        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks in your list:");
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            message.append("\n").append(i + 1).append(". ");
            Task task = taskManager.getTask(i);
            if (task instanceof Event) {
                Event temp = (Event) task;
                message.append(temp.toStringForGUI());
            } else {
                message.append(task);
            }
        }
        return message.toString();
    }
}
