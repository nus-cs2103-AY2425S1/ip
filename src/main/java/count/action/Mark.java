package count.action;

import count.TaskList;
import count.exception.CountException;

/**
 * The Mark class is used to mark the specified task as complete
 */
public class Mark extends Action {
    private TaskList ls;
    private int index;

    /**
     * Constructs Mark object
     * @param ls TaskList being printed out
     * @param index int index of the Task in TaskList to mark as complete
     */
    public Mark(TaskList ls, int index) {
        this.ls = ls;
        this.index = index;
    }

    /**
     * Marks the Task with the index of index as complete
     * @return String for Count's UI to print
     * @throws CountException if the index selected is out of bounds for the TaskList
     */
    @Override
    public String run() throws CountException {
        try {
            if (ls.getTaskList().get(this.index - 1).getCompletion()) {
                return "Croak! This task is already complete!";
            }
            ls.getTaskList().get(this.index - 1).setCompletion(true);
            return "Ribbit, I have marked this task as complete:\n" + ls.getTaskList().get(this.index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Croak! Invalid list index chosen! Choose a number from 1 to "
                    + ls.getTaskList().size() + "\nType 'help' to see correct formatting examples");
        }
    }
}
