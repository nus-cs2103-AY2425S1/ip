package rainy.commands;

import rainy.tasks.TaskTracker;

public class ToDoCommand extends Command {
    private String[] userInput;
    private String taskName;
    private TaskTracker taskTracker;

    public ToDoCommand(String[] userInput, String taskName, TaskTracker taskTracker) {
        this.userInput = userInput;
        this.taskName = taskName;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() {
        if (userInput.length == 1) {
            this.ui.noToDoDescription();
        } else {
            this.taskTracker.addListToDo(taskName);
        }
        return this.taskTracker;
    }
}