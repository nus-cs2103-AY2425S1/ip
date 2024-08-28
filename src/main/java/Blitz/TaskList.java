package blitz;

/* My import */
import task.Task;

/* System import */
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList object with specified initial ArrayList.
     *
     * @param list ArrayList that may or may not contain Task objects for TaskList initialization.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Checks if TaskList is empty.
     *
     * @return True if ArrayList in TaskList is not empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns the number of Task objects in the TaskList.
     *
     * @return The number of elements in the ArrayList of TaskList.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Adds specified Task object to TaskList.
     *
     * @param task Task object that is to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a Task object (specified by index) in the TaskList.
     *
     * @param i Index of the Task object to be deleted.
     * @return Task object that has been deleted.
     */
    public Task deleteTask(int i) {
        return this.list.remove(i);
    }

    /**
     * Returns a Task object (specified by index) from the TaskList.
     *
     * @param i Index of the Task object to be returned.
     * @return Task object that is specified by the index.
     */
    public Task getTask(int i) {
        return this.list.get(i);
    }

    /**
     * Returns all Task objects in the TaskList as an ArrayList of Task objects.
     *
     * @return ArrayList of all the Task objects in the TaskList.
     */
    public ArrayList<Task> getAllTask() {
        return this.list;
    }

    /**
     * Compares two TaskList objects and determines if they are equal.
     *
     * @param o Object to be compared.
     * @return True if both objects are of same reference or all Tasks in both TaskList are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskList t = (TaskList) o;

        if (this.list.size() != t.list.size()) {
            return false;
        }

        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).equals(t.list.get(i))) {
                return false;
            }
        }

        return true;
    }
}
