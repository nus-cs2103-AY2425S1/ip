package count.action;

import count.TaskList;

import count.exception.CountException;

/**
 * The Mark class is used to mark the specified task as complete
 */
public class Mark extends Action {
    TaskList ls;
    int index;

    /**
     * Constructor for Mark
     * @param ls TaskList being printed out
     * @param index int index of the Task in TaskList to mark as complete
     */
    public Mark(TaskList ls, int index) {
        this.ls = ls;
        this.index = index;
    }

    /**
     * The run method mark the Task with the index of index as complete
     * @return String for Count's UI to print
     * @throws CountException if the index selected is out of bounds for the TaskList
     */
    @Override
    public String run() throws CountException {
        try {
            ls.getList().get(this.index - 1).setCompletion(true);
            return "Good job, I have marked this task as complete:\n" + ls.getList().get(this.index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Invalid list index chosen! Choose a number from 1 to " + ls.getList().size() + "\nType 'help' to see correct formatting examples");
        }
    }
}
