package count.action;

import count.TaskList;
import count.exception.CountException;

public class Mark extends Action {
    TaskList ls;
    int index;

    public Mark(TaskList ls, int index) {
        this.ls = ls;
        this.index = index;
    }

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
