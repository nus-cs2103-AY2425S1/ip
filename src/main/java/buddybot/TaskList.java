package buddybot;
//test
import java.util.ArrayList;

/**
 * Class for TaskList
 */
public class TaskList {
    ArrayList<Task> myList;

    /**
     * Constructor for an empty TaskList
     */
    public TaskList() {
        myList = new ArrayList<Task>();
    }

    /**
     * Constructor for a TaskList with a given ArrayList of Tasks
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.myList = taskList;
    }


    /**
     * Return the number of tasks in TaskList
     * @return
     */
    public int size() {
        return myList.size();
    }

    /**
     * Return the Task at the given index
     * @param i
     * @return
     */
    public Task get(int i) {
        return myList.get(i-1);
    }

    /**
     * Add a Task to TaskLst
     * @param t
     */

    public void add(Task t) {
        myList.add(t);
    }

    /**
     * Remove a Task from TaskList
     * @param i
     */

    public void delete(int i) {
        myList.remove(i);
    }

    /**
     * Check if the TaskList is empty
     * @return
     */

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    /**
     * Return the TaskList as a String
     * @return
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i) != null) {
                result.append(myList.get(i).toString()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Return the TaskList as a String the file reader recognises
     * @return
     */
    public String toFile() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i) != null) {
                result.append(myList.get(i).toFile()).append("\n");
            }
        }
        return result.toString();
    }
}
