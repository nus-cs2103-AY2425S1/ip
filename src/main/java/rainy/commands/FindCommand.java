package rainy.commands;

import rainy.tasks.TaskTracker;

/**
 * Processes the user input and searches for any tasks matching the keyword input by user.
 */
public class FindCommand extends Command {
    private TaskTracker taskTracker;
    private String userInput;

    /**
     * Constructs a new <code>FindCommand</code> object.
     * @param taskTracker  The <code>TaskTracker</code> object containing tasks to be searched.
     * @param userInput    The keyword to be searched for.
     */
    public FindCommand(TaskTracker taskTracker, String userInput) {
        this.taskTracker = taskTracker;
        this.userInput = userInput;
    }

    public TaskTracker getResponse() {
        this.taskTracker.findTask(userInput);
        return this.taskTracker;
    }
}
