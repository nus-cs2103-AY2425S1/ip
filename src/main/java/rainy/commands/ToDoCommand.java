package rainy.commands;

import rainy.tasks.TaskTracker;

public class ToDoCommand extends Command {
    private String[] userInput;
    private String taskName;
    private TaskTracker taskTracker;
    private static int NO_DESCRIPTION = 1;

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