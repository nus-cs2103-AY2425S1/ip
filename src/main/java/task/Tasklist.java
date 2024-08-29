package task;
import java.util.ArrayList;

public class Tasklist {
    ArrayList<Task> taskList;

    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    
    /** 
     * Returns a string of all the tasks
     * @return String
     */
    public String getList() {
        String retString = "";
        for (int i = 0; i < taskList.size(); i++) {
            retString += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return retString;
    }

    
    /** 
     * Return string of the task at that position
     * @param pos
     * @return Task
     */
    public Task getStr(int pos) {
        return taskList.get(pos);
    }

    /**
     * Returns the last Task object in the taskList.
     * @return The `getLast()` method is returning the last `Task` object in the `taskList` collection.
     */
    public Task getLast() {
        return taskList.get((this.getSize() - 1));
    }

    /**
     * Return size of tasklist
     * @return The `getSize()` method returns the size of the `taskList`.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a Task object to a taskList.
     * @param t The parameter `t` in the `add` method represents a `Task` object that you want to add to
     * the `taskList`.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    
    /**
     * Removes an element from the `taskList` at the specified position.
     * @param pos index of element to delete
     */
    public void delete(int pos) {
        taskList.remove(pos);
    }
}

