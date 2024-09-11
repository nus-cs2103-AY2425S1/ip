package count;

import java.util.ArrayList;

import count.task.Task;

/**
 * The TaskList class stores the ArrayList&lt;Task&gt;
 * allowing it to be passed by reference
 * @author Kieran Koh Jun Wei
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructs TaskList object if no ArrayList&lt;Task&gt; is provided
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs TaskList object if an ArrayList&lt;Task&gt; is provided
     */
    public TaskList(ArrayList<Task> ls) {
        this.taskList = ls;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
