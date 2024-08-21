package todo;

import chatbot.BeeException;

/**
 * A class that formats error message for
 * invalid inputs related to tasks.
 *
 * @author celeschai
 */
public class TaskException extends BeeException {

    /**
     * Formats error message for different tasks
     *
     * @param missingItems parameters required for task creation
     * @param taskType task type name
     */
    public TaskException(String missingItems, String taskType) {
        super(String.format(
                    "Where is the %s of your %s?\n",
                    missingItems, taskType));
    }
}
