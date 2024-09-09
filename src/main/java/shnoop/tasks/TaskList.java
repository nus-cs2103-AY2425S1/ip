package shnoop.tasks;


import java.util.ArrayList;

/**
 * Represents the list of Tasks that the user can add.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Sets the current ArrayList to the inputted ArrayList.
     *
     * @param tasks TaskList to be loaded into the ArrayList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty ArrayList to begin storing future tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns task at specified index.
     *
     * @param idx Index position of Task in TaskList.
     * @return Task at position 'index' in TaskList.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task to be added to TaskList.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns size of TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a Task as 'marked' (done).
     *
     * @param idx Index position of Task in TaskList to be marked.
     * @return True if the Task was unmarked and is successfully marked, otherwise false.
     */
    public boolean mark(int idx) {
        return tasks.get(idx).markTask();
    }

    /**
     * Marks a Task as 'unmarked' (not done).
     *
     * @param idx Index position of Task in TaskList to be unmarked.
     * @return True if the Task was marked and is successfully unmarked, otherwise false.
     */
    public boolean unmark(int idx) {
        return tasks.get(idx).unmarkTask();
    }

    /**
     * Lists out all the tasks in TaskList.
     *
     * @return String representation of all the tasks in TaskList.
     */
    public String list() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            result += ((i + 1) + ". " + tasks.get(i).toString());
            result += "\n";
        }
        return result;
    }

    /**
     * Deletes selected task at specified index in TaskList.
     *
     * @param idx Index position of Task in TaskList to be deleted.
     * @return Task that was deleted.
     */
    public Task delete(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    /**
     * Looks for Tasks in TaskList that contain a given keyword.
     *
     * @param keyword String to be checked for in the Tasks.
     * @return String representing all Tasks in TaskList containing the given keyword.
     */
    public String find(String keyword) {
        if (keyword.isEmpty() || keyword.isBlank() || keyword.trim().length() <= 0) {
            return "";
        }

        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            String tempTask = tasks.get(i).toString().toLowerCase();
            if (tempTask.contains(keyword.toLowerCase())) {
                result += tempTask + "\n";
            }
        }
        return result;
    }
}
