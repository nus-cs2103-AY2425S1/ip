package jade.command;

import static jade.ui.Ui.INDENT;

import jade.exception.JadeException;
import jade.parser.Parser;
import jade.task.Task;
import jade.task.TaskManager;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private final Parser parser;
    private final String command;

    /**
     * Constructs an AddCommand object with the specified TaskManager, Parser, and command.
     *
     * @param taskManager The TaskManager to manage the tasks.
     * @param parser The Parser to parse the command and create a Task.
     * @param command The command string containing task details.
     */
    public AddCommand(TaskManager taskManager, Parser parser, String command) {
        super(taskManager);

        assert taskManager != null : "TaskManager should not be null";
        assert parser != null : "Parser should not be null";
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        this.parser = parser;
        this.command = command;
    }

    @Override
    public String run() {
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

    @Override
    public String runForGui() {
        try {
            Task newTask = parser.parseTaskCommand(command);
            if (newTask != null) {
                taskManager.addTask(newTask);
                return displayTaskAddedMessageForGui(newTask);
            }
            return null;
        } catch (JadeException e) {
            return e.getMessage();
        }
    }

    private String displayTaskAddedMessageForGui(Task task) {
        int taskCount = taskManager.getTaskCount();
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n").append("  ").append(task);

        if (taskCount == 1) {
            message.append("\n").append("Now you have 1 task in the list.");
        } else {
            message.append("\n").append(String.format("Now you have %d tasks in the list.", taskCount));
        }

        return message.toString();
    }
}
