package count.action;

import count.TaskList;
import count.exception.CountException;
import count.task.Task;


/**
 * The Delete class is used to add tasks to the TaskList
 */
public class Delete extends Action {
    private TaskList ls;
    private int index;

    /**
     * Constructor for Delete
     * @param ls TaskList to delete the task from
     * @param index int index of the Task in TaskList to delete
     */
    public Delete(TaskList ls, int index) {
        this.ls = ls;
        this.index = index;
    }

    /**
     * The run method deletes the Task with the index of index from TaskList
     * @return String for Count's UI to print
     * @throws CountException if the index selected is out of bounds for the TaskList
     */
    @Override
    public String run() throws CountException {
        try {
            Task taskRemoved = ls.getList().get(this.index - 1);
            ls.getList().remove(this.index - 1);
            return "Ribbit, I have removed this task from the list:\n"
                    + taskRemoved.toString() + "\nYou now have " + ls.getList().size() + " task(s) in your list";
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Croak! Invalid list index chosen! Choose a number from 1 to "
                    + ls.getList().size() + "\nType 'help' to see correct formatting examples");
        }
    }
}
