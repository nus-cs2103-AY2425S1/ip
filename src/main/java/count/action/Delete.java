package count.action;

import count.task.Task;
import count.TaskList;
import count.exception.CountException;
public class Delete extends Action {
    TaskList ls;
    int index;

    public Delete(TaskList ls, int index) {
        this.ls = ls;
        this.index = index;
    }

    @Override
    public String run() throws CountException {
        try {
            Task curr = ls.getList().get(this.index - 1);
            ls.getList().remove(this.index - 1);
            return "Got it, I have removed this task from the list:\n" + curr.toString() + "\nYou now have " + ls.getList().size() + " task(s) in your list";
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Invalid list index chosen! Choose a number from 1 to " + ls.getList().size() + "\nType 'help' to see correct formatting examples");
        }
    }
}
