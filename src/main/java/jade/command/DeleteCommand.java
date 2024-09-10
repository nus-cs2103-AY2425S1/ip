package jade.command;

import static jade.ui.Ui.INDENT;

import jade.exception.JadeException;
import jade.task.Task;
import jade.task.TaskManager;

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
        this.command = command;
    }

    @Override
    public String run() {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskManager.isValidTaskIndex(taskIndex)) {
                Task removedTask = taskManager.getTask(taskIndex);
                taskManager.deleteTask(taskIndex);
                int taskCount = taskManager.getTaskCount();

                StringBuilder message = new StringBuilder();
                message.append(INDENT).append("Noted. I've removed this task:\n")
                        .append(INDENT).append("  ").append(removedTask);
                if (taskCount <= 1) {
                    message.append("\n").append(INDENT)
                            .append(String.format("Now you have %d task in the list.", taskCount));
                } else {
                    message.append("\n").append(INDENT)
                            .append(String.format("Now you have %d tasks in the list.", taskCount));
                }

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
            if (taskManager.isValidTaskIndex(taskIndex)) {
                Task removedTask = taskManager.getTask(taskIndex);
                taskManager.deleteTask(taskIndex);
                int taskCount = taskManager.getTaskCount();

                StringBuilder message = new StringBuilder();
                message.append("Noted. I've removed this task:\n")
                        .append("  ").append(removedTask);
                if (taskCount <= 1) {
                    message.append("\n").append(String.format("Now you have %d task in the list.", taskCount));
                } else {
                    message.append("\n").append(String.format("Now you have %d tasks in the list.", taskCount));
                }

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
