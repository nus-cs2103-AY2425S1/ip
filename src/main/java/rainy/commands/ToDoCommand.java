package rainy.commands;

import rainy.tasks.TaskTracker;

/**
 * Processe the user input and initializes a new <code>ToDo</code> object based on user description.
 */
public class ToDoCommand extends Command {
    private static final int NO_DESCRIPTION = 1;
    private String[] userInput;
    private String taskName;
    private TaskTracker taskTracker;

    /**
     * Constructs a new <code>ToDoCommand</code> object.
     * @param userInput     List of String objects split by delimiter " ".
     * @param taskName      Task Description.
     * @param taskTracker   <code>TaskTracker</code> object to be updated.
     */
    public ToDoCommand(String[] userInput, String taskName, TaskTracker taskTracker) {
        this.userInput = userInput;
        this.taskName = taskName;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() {
        if (userInput.length == NO_DESCRIPTION) {
            this.ui.noToDoDescription();
        } else {
            this.taskTracker.addListToDo(taskName);
        }
        return this.taskTracker;
    }
}
