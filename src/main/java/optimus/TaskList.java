package optimus;

import java.util.List;
import java.util.ArrayList;

//help of chatGPT to generate better documentation
/**
 * The {@code TaskList} class manages a list of {@code Task} objects.
 * It supports adding, deleting, retrieving tasks, and finding tasks that match a specific keyword.
 */
public class TaskList {
    private List<Task> record;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.record = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with a pre-existing list of tasks.
     *
     * @param tasks The list of {@code Task} objects to initialize the task list with.
     */
    public TaskList(List<Task> tasks) {
        this.record = tasks;
    }

    /**
     * Adds a {@code Task} to the task list.
     *
     * @param task The {@code Task} object to be added.
     */
    public void addTask(Task task) {
        record.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed from the list.
     */
    public void deleteTask(int index) {
        record.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The {@code Task} object at the specified index.
     */
    public Task getTask(int index) {
        return record.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSizeOfRecord() {
        return record.size();
    }

    /**
     * Retrieves all tasks from the task list.
     *
     * @return A list of all {@code Task} objects in the task list.
     */
    public List<Task> getTasks() {
        return record;
    }

    /**
     * Finds and returns tasks that contain the specified keyword(s) in their description.
     *
     * @param keyword The keyword or keywords used to search for tasks (separated by spaces).
     * @param ui      The {@code Ui} object to handle user interface messages and responses.
     * @return A string containing either a list of matching tasks or an error message if no tasks are found.
     */
    public String findTasks(String keyword, Ui ui) {
        String[] keywords = keyword.split("\\s+");
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : record) {
            boolean isMatch = true;
            String description = task.getDescription().toLowerCase();

            for (String kw : keywords) {
                if (!description.contains(kw.toLowerCase())) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            return ui.printError("No matching tasks found. Try again");
        } else {
            return ui.listFoundTasks(matchingTasks);
        }
    }
}
