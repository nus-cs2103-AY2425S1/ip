package evan.command;

import evan.service.TaskList;

/**
 * Represents a command that the chatbot can execute to list out the currently stored tasks.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) {
        String result = taskList.toString();
        if (result.isEmpty()) {
            return "Hooray! Your task list is empty!";
        }
        return result;
    }
}
