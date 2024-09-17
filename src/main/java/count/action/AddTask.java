package count.action;

import count.TaskList;
import count.exception.CountException;
import count.task.Task;

/**
 * The AddTask class is used to add tasks to the TaskList
 */
public class AddTask extends Action {
    private TaskList ls;
    private Task task;
    private String filePath;

    /**
     * Constructs AddTask object
     * @param ls TaskList to add the task into
     * @param t Task to add into the TaskList
     * @param filePath String of the file path to create Save objects
     */
    public AddTask(TaskList ls, Task t, String filePath) {

        this.ls = ls;
        this.task = t;
        this.filePath = filePath;
    }
    /**
     * Adds the object's Task to the object's TaskList and saves the TaskList afterward
     * @return String for Count's UI to print
     * @throws CountException on saving exception
     */
    @Override
    public String run() throws CountException {
        ls.getTaskList().add(this.task);
        String saveString = new Save(this.ls, this.filePath).run();
        return "Ribbit, added the following task:\n" + this.task.toString()
                + "\nYou now have " + ls.getTaskList().size() + " task(s) in your list";
    }
}
