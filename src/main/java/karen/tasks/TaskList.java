package karen.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class which manages the List of Tasks and contains methods to modify it
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public void markTask(int i) {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    /**
     * Returns an array of all the taskStrings for each <code>Task</code> in the <code>TaskList</code>
     * @return String[]
     */
    public String[] toTaskStrings() {
        if (this.tasks.isEmpty()) {
            return new String[] {};
        } else {
            String[] taskStrings = new String[this.tasks.size()];
            for (int i = 0; i < this.tasks.size(); i++) {
                taskStrings[i] = this.tasks.get(i).toString();
            }
            return taskStrings;
        }
    }

    /**
     * Returns a {@code List<String>} containing each Task's fileString
     * @return {@code List<String>}
     */
    public List<String> toFileStrings() {
        if (this.tasks.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<String> fileStrings = new ArrayList<>();
            for (Task t : tasks) {
                fileStrings.add(t.toFileString());
            }
            return fileStrings;
        }
    }

    /**
     * Searches the List by name with the specified substring
     * @param searchWord String representing the substring to search with
     * @return a List of Task containing <code>Tasks</code> which satisfy the search condition
     */
    public List<Task> searchTasks(String searchWord) {
        List<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            int found = t.getName().indexOf(searchWord);
            if (found != -1) { //found is -1 if task name does not contain searchWord
                result.add(t);
            }
        }
        return result;
    }

    /**
     * A utility method to convert a List of Task into a <code>TaskList</code>
     * @param listOfTask the <code>List</code> to convert
     */
    public static TaskList fromList(List<Task> listOfTask) {
        TaskList taskList = new TaskList();
        for (Task t : listOfTask) {
            taskList.addTask(t);
        }

        return taskList;
    }
}
