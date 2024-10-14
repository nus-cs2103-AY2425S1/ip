package friday.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (isKeywordEmpty(keyword)) {
            return showKeywordErrorMessage();
        }

        List<Task> matchedTasks = searchTask(keyword);
        if (matchedTasks.isEmpty()) {
            return showNoTasksFoundMessage();
        } else {
            return displayMatchingTasks(matchedTasks);
        }
    }

    private boolean isKeywordEmpty(String keyword) {
        return keyword.trim().isEmpty();
    }

    private String showKeywordErrorMessage() {
        return "Please provide a keyword to search for.";
    }

    private List<Task> searchTask(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            assert task != null : "Task should not be null";
            if (isTaskMatchingKeyword(task, lowerCaseKeyword)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }

    private boolean isTaskMatchingKeyword(Task task, String keyword) {
        String description = task.toString().toLowerCase();
        return Arrays.asList(description.split("\\s+")).contains(keyword);
    }

    private String displayMatchingTasks(List<Task> matchedTasks) {
        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            matchingTasks.append("\n").append(i + 1).append(".").append(matchedTasks.get(i));
        }
        return matchingTasks.toString();
    }

    private String showNoTasksFoundMessage() {
        return "No matching tasks found.";
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws FridayException {
        if (index < 0 || index >= this.getSize()) {
            throw new FridayException("The task number provided is invalid.");
        }
        return tasks.get(index);
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
