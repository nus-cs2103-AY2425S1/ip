package jade.command;

import static jade.ui.Ui.INDENT;

import jade.exception.JadeException;
import jade.task.TaskManager;

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
    public String run() {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            assert taskIndex >= 0 : "Task index should not be negative.";

            if (taskManager.isValidTaskIndex(taskIndex)) {
                taskManager.markTask(taskIndex, isDone);
                String status = isDone
                        ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:";
                StringBuilder message = new StringBuilder();
                message.append(INDENT).append(status).append("\n")
                        .append(INDENT).append("  ").append(taskManager.getTask(taskIndex));
                return displayMessage(message.toString());
            } else {
                throw new JadeException("Hmm, no such task. Try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return displayErrorMessage("Please specify a valid task number in the format:\n"
                    + INDENT + "  mark <index>");
        } catch (JadeException e) {
            return displayErrorMessage(e.getMessage());
        }
    }

    @Override
    public String runForGui() {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            assert taskIndex >= 0 : "Task index should not be negative.";

            if (taskManager.isValidTaskIndex(taskIndex)) {
                taskManager.markTask(taskIndex, isDone);
                String status = isDone
                        ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:";
                StringBuilder message = new StringBuilder();
                message.append(status).append("\n")
                        .append("  ").append(taskManager.getTask(taskIndex));
                return message.toString();
            } else {
                throw new JadeException("Hmm, no such task. Try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please specify a valid task number in the format:\n  mark <index>";
        } catch (JadeException e) {
            return e.getMessage();
        }
    }
}
