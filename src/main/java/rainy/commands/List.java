package rainy.commands;

import rainy.tasks.Task;
import rainy.tasks.TaskTracker;

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