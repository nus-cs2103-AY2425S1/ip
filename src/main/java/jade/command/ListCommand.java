package jade.command;

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
        assert taskManager != null : "TaskManager should not be null";
    }

    @Override
    public String run() {
        return displayTaskListMessage(FOR_TEXT_UI);
    }

    @Override
    public String runForGui() {
        return displayTaskListMessage(FOR_GUI);
    }

    private String displayTaskListMessage(boolean forGui) {
        StringBuilder message = new StringBuilder();

        if (taskManager.getTaskCount() == 0) {
            message.append("No tasks in your list.");
            return displayMessage(forGui, message.toString());
        }

        message.append("Here are the task(s) in your list:");
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            message.append("\n");
            indentIfNotGui(forGui, message);
            message.append(i + 1).append(". ");

            Task task = taskManager.getTask(i);
            if (forGui && task instanceof Event) {
                Event temp = (Event) task;
                message.append(temp.toStringForGui());
            } else {
                message.append(task);
            }
        }

        return displayMessage(forGui, message.toString());
    }
}
