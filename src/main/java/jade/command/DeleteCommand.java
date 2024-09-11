package jade.command;

import static jade.ui.Ui.INDENT;

import jade.exception.JadeException;
import jade.task.Task;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String command;

    /**
     * Constructs a DeleteCommand object with the specified TaskManager and command.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param command The command string containing task details.
     */
    public DeleteCommand(TaskManager taskManager, String command) {
        super(taskManager);

        assert taskManager != null : "TaskManager should not be null";
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        this.command = command;
    }

    @Override
    public String runForGui() {
        try {
            return displayTaskDeletedMessage(FOR_GUI);
        } catch (JadeException e) {
            return e.getMessage();
        }
    }

    @Override
    public String run() {
        try {
            return displayTaskDeletedMessage(FOR_TEXT_UI);
        } catch (JadeException e) {
            return Ui.formatTextUiMessage(e.getMessage());
        }
    }

    private String displayTaskDeletedMessage(boolean forGui) throws JadeException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            assert taskIndex >= 0 : "Task index should not be negative.";

            if (!taskManager.isValidTaskIndex(taskIndex)) {
                throw new JadeException("Hmm, no such task. Try again.");
            }

            Task removedTask = taskManager.getTask(taskIndex);
            taskManager.deleteTask(taskIndex);
            int taskCount = taskManager.getTaskCount();

            StringBuilder message = new StringBuilder();
            message.append("Noted. I've removed this task:\n");
            indentIfNotGui(forGui, message);
            message.append("  ").append(removedTask);

            if (taskCount <= 1) {
                message.append("\n");
                indentIfNotGui(forGui, message);
                message.append(String.format("Now you have %d task in the list.", taskCount));
            } else {
                message.append("\n");
                indentIfNotGui(forGui, message);
                message.append(String.format("Now you have %d tasks in the list.", taskCount));
            }

            return displayMessage(forGui, message.toString());
        } catch (NumberFormatException e) {
            StringBuilder message = new StringBuilder();
            message.append("Please specify a valid task number in the format:\n");
            indentIfNotGui(forGui, message);
            message.append("  delete <index>");

            throw new JadeException(message.toString());
        }
    }
}
