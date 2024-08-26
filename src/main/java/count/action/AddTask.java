package count.action;

import count.TaskList;
import count.task.Task;
public class AddTask extends Action {
    TaskList ls;
    Task task;
    public AddTask(TaskList ls, Task t) {

        this.ls = ls;
        this.task = t;
    }

    @Override
    public String run() {
        ls.getList().add(this.task);
        return "Added the following task:\n" + this.task.toString() +"\nYou now have " + ls.getList().size() + " task(s) in your list";
    }
}
