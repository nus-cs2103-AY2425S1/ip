package count;

import java.util.ArrayList;

import count.task.Task;

/**
 * The TaskList class stores the ArrayList&lt;Task&gt;
 * allowing it to be passed by reference
 * @author Kieran Koh Jun Wei
 */
public class TaskList {
    protected ArrayList<Task> ls;

    /**
     * Constructor for TaskList if no ArrayList&lt;Task&gt; is provided
     */
    public TaskList() {
        this.ls = new ArrayList<>();
    }

    /**
     * Constructor for TaskList if an ArrayList&lt;Task&gt; is provided
     */
    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public ArrayList<Task> getList() {
        return this.ls;
    }

}
