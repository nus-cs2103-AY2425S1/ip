package darwin.command;

import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * StartCommand class to represent a command to start the chat bot.
 */
public class StartCommand extends Command {
    private static final String START_MSG = "Hello! I'm %s\nWhat can I do for you?";
    private String name;

    /**
     * Creates a new start command.
     * @param name name of the chat bot
     */
    public StartCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the start command.
     * @param taskManager task manager to execute the command
     * @param ui user interface to display messages
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.send(String.format(START_MSG, this.name));
    }
}
