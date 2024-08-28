package count.action;

import java.util.ArrayList;

import count.TaskList;

import count.task.Task;

import count.exception.CountException;

/**
 * The find class finds tasks that contain the key passed to it
 */
public class Find extends Action {
    TaskList ls;
    String key;

    /**
     * Constructor for Find
     * @param ls TaskList being searched
     * @param key String to search for
     */
    public Find(TaskList ls, String key) {
        this.ls = ls;
        this.key = key;
    }

    /**
     * The run method finds all Tasks containing the given key
     * appending it to the return String
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        String ans = "Here are the matching tasks in your list\n";
        ArrayList<Task> list = this.ls.getList();
        int numberOfSearchHits = 0;

        for (Task t: list) {
            if (t.getDescription().contains(this.key)) {
                numberOfSearchHits++;
                ans += numberOfSearchHits + ". " + t.toString() + "\n";
            }
        }
        return ans;
    }
}
