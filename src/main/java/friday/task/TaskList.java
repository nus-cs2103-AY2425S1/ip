package friday.task;

import java.util.ArrayList;

import friday.util.FridayException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws FridayException If the index is out of range.
     */
    public void deleteTask(int index) throws FridayException {
        tasks.remove(index);
    }

    /**
     * Searches for tasks containing the specified keyword in their description.
     * <p>
     * The method checks if the keyword is empty and prompts the user to provide a valid keyword if it is.
     * It then iterates through the list of tasks, comparing each task's description with the keyword.
     * Matching tasks are printed with their index in the list. If no tasks match the keyword, a message is displayed.
     * </p>
     *
     * @param keyword The keyword to search for in the task descriptions.
     *                The search is case-insensitive and looks for exact matches of words in the description.
     * @return The string representation of tasks in the task list matching the keyword.
     */
    public String findTasks(String keyword) {
        assert keyword != null : "Search keyword cannot be null";

        if (keyword.trim().isEmpty()) {
            return "Please provide a keyword to search for.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null : "Task should not be null";

            String description = task.toString().toLowerCase();
            String[] words = description.split("\\s+");

            for (String word : words) {
                assert word != null : "Word in task description should not be null";
                if (word.equals(keyword.toLowerCase())) {
                    response.append("\n").append(i + 1).append(".").append(task);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            return "No matching tasks found.";
        }

        return response.toString();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }
}
