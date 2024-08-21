import java.util.ArrayList;

public class List {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task Task object that is to be added to the array list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     *  Returns the String representation of the list.
     *
     *  @return String representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        if (tasks.isEmpty()) {
            return "There are no tasks in your list.";
        }

        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     * @return True if the task is successfully marked as done, false otherwise.
     */
    public boolean markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            return true;
        } else {
            return false;
        }
    }

    /** Marks a task as not done.
     *
     * @param index Index of the task to be marked as not done.
     * @return True if the task is successfully marked as not done, false otherwise.
     */
    public boolean markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of task at the specified index.
     * This is to ensure that the task is not modified unintentionally
     *
     * @param index Index of the task to be returned.
     * @return Task at the specified index.
     */
    public String get(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return tasks.size();
    }
}
