package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

/**
 * Processes the mark user input and calls the markDone method for <code>TaskTracker</code>.
 */
public class Mark extends Command {
    private static final int INVALID_RESPONSE = -1;
    private int validResponse;
    private TaskTracker taskTracker;

    /**
     * Constructs a new Mark object.
     * @param validResponse   If validResponse is not -1, it determines the index number of the task in task list.
     * @param taskTracker     The <code>TaskTracker</code> object to be updated.
     */
    public Mark(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (validResponse != INVALID_RESPONSE) {
            this.taskTracker.markDone(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}
