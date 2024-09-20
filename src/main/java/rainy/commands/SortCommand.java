package rainy.commands;

import rainy.tasks.TaskTracker;

/**
 * Processes and sorts the task list according to date of task. For ToDo tasks without a date, they
 * will be sorted to the bottom of the list.
 */
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
