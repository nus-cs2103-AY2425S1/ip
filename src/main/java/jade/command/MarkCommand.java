package jade.command;

import static jade.ui.Ui.INDENT;

import jade.exception.JadeException;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Represents a command to mark or unmark a task.
 */
public class MarkCommand extends Command {
    private final String command;
    private final boolean isDone;

    /**
     * Constructs a MarkCommand object with the specified TaskManager and command.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param command The command string containing task details.
     * @param isDone A boolean indicating whether the task should be marked as done (`true`) or undone (`false`).
     */
    public MarkCommand(TaskManager taskManager, String command, boolean isDone) {
        super(taskManager);

        assert taskManager != null : "TaskManager should not be null";
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        this.command = command;
        this.isDone = isDone;
    }

    @Override
    public String runForGui() {
        try {
            return displayMarkedTaskMessage(FOR_GUI);
        } catch (JadeException e) {
            return e.getMessage();
        }
    }

    @Override
    public String run() {
        try {
            return displayMarkedTaskMessage(FOR_TEXT_UI);
        } catch (JadeException e) {
            return Ui.formatTextUiMessage(e.getMessage());
        }
    }

    private String displayMarkedTaskMessage(boolean forGui) throws JadeException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            assert taskIndex >= 0 : "Task index should not be negative.";

            if (!taskManager.isValidTaskIndex(taskIndex)) {
                throw new JadeException("Hmm, no such task. Try again.");
            }

            taskManager.markTask(taskIndex, isDone);
            String status = isDone
                    ? "Nice! I've marked this task as done:"
                    : "OK, I've marked this task as not done yet:";

            StringBuilder message = new StringBuilder();
            message.append(status).append("\n");
            indentIfNotGui(forGui, message);
            message.append("  ").append(taskManager.getTask(taskIndex));

            return displayMessage(forGui, message.toString());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            StringBuilder message = new StringBuilder();
            message.append("Please specify a valid task number in the format:\n");
            indentIfNotGui(forGui, message);
            message.append("  mark <index>");

            throw new JadeException(message.toString());
        }
    }
}
