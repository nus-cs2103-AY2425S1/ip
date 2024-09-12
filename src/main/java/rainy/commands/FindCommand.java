package rainy.commands;

import rainy.tasks.TaskTracker;

public class FindCommand extends Command {
    private TaskTracker taskTracker;
    private String userInput;

    public FindCommand(TaskTracker taskTracker, String userInput) {
        this.taskTracker = taskTracker;
        this.userInput = userInput;
    }

    public TaskTracker getResponse() {
        this.taskTracker.findTask(userInput);
        return this.taskTracker;
    }
}