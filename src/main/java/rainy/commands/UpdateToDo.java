package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.TaskTracker;

/**
 * Processes the user input and attempts to update the <code>ToDo</code> object with new parameters.
 */
public class UpdateToDo extends UpdateCommand {
    public UpdateToDo(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidIndexException {
        String toDoName = this.updateParameters[0];
        this.taskTracker.updateToDo(validResponse - 1, toDoName);
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }
}
