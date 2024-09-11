package jade.command;

import jade.exception.JadeException;
import jade.parser.Parser;
import jade.task.Task;
import jade.task.TaskManager;
import jade.ui.Ui;

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
    public String runForGui() {
        try {
            return displayTaskAddedMessage(FOR_GUI);
        } catch (JadeException e) {
            return e.getMessage();
        }
    }

    @Override
    public String run() {
        try {
            return displayTaskAddedMessage(FOR_TEXT_UI);
        } catch (JadeException e) {
            return Ui.formatTextUiMessage(e.getMessage());
        }
    }

    private String displayTaskAddedMessage(boolean forGui) throws JadeException {
        Task newTask = parser.parseTaskCommand(command);
        if (newTask == null) {
            return null;
        }
        taskManager.addTask(newTask);
        int taskCount = taskManager.getTaskCount();

        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n");
        indentIfNotGui(forGui, message);
        message.append("  ").append(newTask);

        if (taskCount == 1) {
            message.append("\n");
            indentIfNotGui(forGui, message);
            message.append("Now you have 1 task in the list.");
        } else {
            message.append("\n");
            indentIfNotGui(forGui, message);
            message.append(String.format("Now you have %d tasks in the list.", taskCount));
        }

        return displayMessage(forGui, message.toString());
    }
}
