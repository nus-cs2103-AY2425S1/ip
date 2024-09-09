package count.action;

import count.TaskList;
import count.exception.CountException;

/**
 * The Unmark class is used to mark the specified task as incomplete
 */
public class Unmark extends Action {
    private TaskList ls;
    private int index;

    /**
     * Constructor for Unmark
     * @param ls TaskList being printed out
     * @param index int index of the Task in TaskList to mark as incomplete
     */
    public Unmark(TaskList ls, int index) {
        this.ls = ls;
        this.index = index;
    }

    /**
     * The run method marks the Task with the index of index as incomplete
     * @return String for Count's UI to print
     * @throws CountException if the index selected is out of bounds for the TaskList
     */
    @Override
    public String run() throws CountException {
        try {
            if (!ls.getList().get(this.index - 1).getCompletion()) {
                return "Croak! This task is already incomplete!";
            }
            ls.getList().get(this.index - 1).setCompletion(false);
            return "Ribbit, I have marked this task as incomplete:\n" + ls.getList().get(this.index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Croak! Invalid list index chosen! Choose a number from 1 to "
                    + ls.getList().size() + "\nType 'help' to see correct formatting examples");
        }
    }
}

