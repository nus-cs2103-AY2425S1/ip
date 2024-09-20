package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

/**
 * Processes the user input and attempts to unmark a particular task specified by user.
 */
public class Unmark extends Command {
    private static final int INVALID_RESPONSE = -1;
    private int validResponse;
    private TaskTracker taskTracker;

    /**
     * Constructs a new <code>Unmark</code> object.
     * @param validResponse  If the value of validResponse is -1, the input is invalid, else
     *                       validResponse refers to the index number of the task list.
     * @param taskTracker    <code>TaskTracker</code> object to be updated.
     */
    public Unmark(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (validResponse != INVALID_RESPONSE) {
            this.taskTracker.unmarkDone(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}
