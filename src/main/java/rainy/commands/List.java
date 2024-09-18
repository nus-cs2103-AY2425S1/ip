package rainy.commands;

import rainy.tasks.TaskTracker;

/**
 * Processes the user input and prints the list into the console.
 */
public class List extends Command {
    private TaskTracker taskTracker;

    public List(TaskTracker taskTracker) {
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() {
        System.out.println(this.taskTracker.printList());
        return new TaskTracker();
    }
}
