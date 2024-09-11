package count.action;

import java.util.ArrayList;

import count.TaskList;
import count.task.Task;


/**
 * The Find class finds tasks that contain the key passed to it
 */
public class Find extends Action {
    private TaskList ls;
    private String key;

    /**
     * Constructs Find object
     * @param ls TaskList being searched
     * @param key String to search for
     */
    public Find(TaskList ls, String key) {
        this.ls = ls;
        this.key = key;
    }

    /**
     * Finds all Tasks containing the given key
     * appending it to the return String
     * @return String for Count's UI to print
     */
    @Override
    public String run() {
        String ans = "Ribbit, here are the matching tasks in your list\n";
        ArrayList<Task> list = this.ls.getTaskList();
        int numberOfSearchHits = 0;

        for (Task t: list) {
            if (t.getDescription().contains(this.key)) {
                numberOfSearchHits++;
                ans += numberOfSearchHits + ". " + t.toString() + "\n";
            }
        }
        if (numberOfSearchHits == 0) {
            return "Croak! There are no matching tasks in your list\n";
        }
        return ans;
    }
}
