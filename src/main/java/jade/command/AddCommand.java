package jade.command;

import jade.exception.JadeException;
import jade.parser.Parser;
import jade.task.Task;
import jade.task.TaskManager;
import static jade.ui.Ui.INDENT;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private final Parser parser;
    private final String command;

    public AddCommand(TaskManager taskManager, Parser parser, String command) {
        super(taskManager);
        this.parser = parser;
        this.command = command;
    }

    @Override
    public String run() throws JadeException {
        try {
            Task newTask = parser.parseTaskCommand(command);
            if (newTask != null) {
                taskManager.addTask(newTask);
                return displayTaskAddedMessage(newTask);
            }
            return null;
        } catch (JadeException e) {
            return displayErrorMessage(e.getMessage());
        }
    }

    private String displayTaskAddedMessage(Task task) {
        int taskCount = taskManager.getTaskCount();
        StringBuilder message = new StringBuilder();
        message.append(INDENT).append("Got it. I've added this task:\n")
                .append(INDENT).append("  ").append(task);

        if (taskCount == 1) {
            message.append("\n").append(INDENT).append("Now you have 1 task in the list.");
        } else {
            message.append("\n").append(INDENT)
                    .append(String.format("Now you have %d tasks in the list.", taskCount));
        }

        return displayMessage(message.toString());
    }
}
