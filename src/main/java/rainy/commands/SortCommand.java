package rainy.commands;

import rainy.tasks.TaskTracker;

public class SortCommand extends Command {
    private TaskTracker taskTracker;

    public SortCommand(TaskTracker taskTracker) {
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() {
        this.taskTracker.sortList();
        System.out.println(this.taskTracker.printList());
        return this.taskTracker;
    }
}