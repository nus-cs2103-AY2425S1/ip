package evan.command;

import evan.service.TaskList;

/**
 * Represents a command that the chatbot can execute to give the user guidance.
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) {
        return "";
    }
}
