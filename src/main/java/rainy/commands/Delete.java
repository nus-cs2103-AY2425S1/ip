package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.TaskTracker;

/**
 * Represents an instruction which processes and outputs an appropriate response based on user input for task deletion.
 */
public class Delete extends Command {
    private static final int INVALID_RESPONSE = -1;
    private int validResponse;
    private TaskTracker taskTracker;

    /**
     * Constructs a new Delete object.
     * @param validResponse  <code>Integer</code> to represent the index number of the task, or if it is invalid.
     * @param taskTracker    <code>TaskTracker</code> object to be updated.
     */
    public Delete(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException {
        if (validResponse != INVALID_RESPONSE) {
            this.taskTracker.delete(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}
