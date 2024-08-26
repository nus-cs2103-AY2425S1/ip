package count;

import count.task.Task;
import count.exception.CountException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> ls;

    public TaskList() {
        this.ls = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    private String delete(int i) throws CountException {
        try {
            Task curr = ls.get(i - 1);
            ls.remove(i - 1);
            return "Got it, I have removed this task from the list:\n" + curr.toString() + "\nYou now have " + ls.size() + " task(s) in your list";
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Invalid list index chosen! Choose a number from 1 to " + ls.size() + "\nType 'help' to see correct formatting examples");
        }
    }


    public ArrayList<Task> getList() {
        return this.ls;
    }

}
