package count.action;

import count.TaskList;
import count.task.Task;

/**
 * The AddTask class is used to add tasks to the TaskList
 */
public class AddTask extends Action {
    private TaskList ls;
    private Task task;

    /**
     * Constructor for AddTask
     * @param ls TaskList to add the task into
     * @param t Task to add into the TaskList
     */
    public AddTask(TaskList ls, Task t) {

        this.ls = ls;
        this.task = t;
    }

    /**
     * The run method adds the object's Task to the object's TaskList
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        ls.getList().add(this.task);
        return "Ribbit, added the following task:\n" + this.task.toString()
                + "\nYou now have " + ls.getList().size() + " task(s) in your list";
    }
}
