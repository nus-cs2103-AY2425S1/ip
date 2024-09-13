package rainy.commands;

import rainy.database.Parser;
import rainy.database.UI;
import rainy.tasks.TaskTracker;

public class List extends Command {
    private TaskTracker taskTracker;

    public List(TaskTracker taskTracker) {
        this.taskTracker = taskTracker;
    }

    public void getResponse() {
        System.out.println(this.taskTracker.getList());
    }


}