package jade.command;

import jade.exception.JadeException;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Represents a command to mark or unmark a task.
 */
public class MarkCommand extends Command {
    private final String command;
    private final boolean isDone;

    public MarkCommand(TaskManager taskManager, String command, boolean isDone) {
        super(taskManager);
        this.command = command;
        this.isDone = isDone;
    }

    @Override
    public String run() throws JadeException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskManager.isValidTaskIndex(taskIndex)) {
                taskManager.markTask(taskIndex, isDone);
                String status = isDone
                        ? "Nice! I've marked this task as done:"
                        : "OK, I've marked this task as not done yet:";
                StringBuilder message = new StringBuilder();
                message.append(Ui.INDENT).append(status).append("\n")
                        .append(Ui.INDENT).append("  ").append(taskManager.getTask(taskIndex));
                return displayMessage(message.toString());
            } else {
                throw new JadeException("Hmm, no such task. Try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return displayErrorMessage("Please specify a valid task number in the format:\n"
                    + Ui.INDENT + "  mark <index>");
        } catch (JadeException e) {
            return displayErrorMessage(e.getMessage());
        }
    }
}
