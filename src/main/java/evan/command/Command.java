package evan.command;

import evan.exception.NoSuchTaskException;
import evan.service.TaskList;

/**
 * Represents a Command that the chatbot can execute.
 */
public abstract class Command {
    /**
     * Executes the command and returns the string response.
     *
     * @param taskList TaskList that the chatbot currently stores.
     * @return The String response that Evan will reply the user with.
     */
    public abstract String execute(TaskList taskList) throws NoSuchTaskException;
}


