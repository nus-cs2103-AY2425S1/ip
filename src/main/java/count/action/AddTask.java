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
     * Constructs AddTask object
     * @param ls TaskList to add the task into
     * @param t Task to add into the TaskList
     */
    public AddTask(TaskList ls, Task t) {

        this.ls = ls;
        this.task = t;
    }

    /**
     * Adds the object's Task to the object's TaskList
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        ls.getTaskList().add(this.task);
        return "Ribbit, added the following task:\n" + this.task.toString()
                + "\nYou now have " + ls.getTaskList().size() + " task(s) in your list";
    }
}
