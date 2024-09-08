package jade.command;

import jade.exception.JadeException;
import jade.task.Task;
import jade.task.TaskManager;
import jade.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String command;

    public DeleteCommand(TaskManager taskManager, String command) {
        super(taskManager);
        this.command = command;
    }

    @Override
    public String run() throws JadeException {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskManager.isValidTaskIndex(taskIndex)) {
                Task removedTask = taskManager.getTask(taskIndex);
                taskManager.deleteTask(taskIndex);
                int taskCount = taskManager.getTaskCount();

                StringBuilder message = new StringBuilder();
                message.append(Ui.INDENT).append("Noted. I've removed this task:\n")
                        .append(Ui.INDENT).append("  ").append(removedTask);
                if (taskCount <= 1) {
                    message.append("\n").append(Ui.INDENT)
                            .append(String.format("Now you have %d task in the list.", taskCount));
                } else {
                    message.append("\n").append(Ui.INDENT)
                            .append(String.format("Now you have %d tasks in the list.", taskCount));
                }

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
