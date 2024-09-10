package task;

import java.util.ArrayList;

/**
 * Class to store {@code Task} objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Stores the list of tasks.
     * @param tasks {@code ArrayList} of tasks to be stored.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Counts the current number of tasks in the list.
     *
     * @return the current number of tasks.
     */
    public int numTasks() {
        int count = 0;
        for (Task task : this.tasks) {
            if (task != null) {
                count += 1;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * Marks a particular task in the list.
     *
     * @param taskIndex Index of the task to be marked.
     * @return True if able to mark, false otherwise.
     */
    public boolean markTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex);
        boolean isSuccessful;
        if (task.getStatusIcon() == 'X') {
            isSuccessful = false; // Has been marked already
        } else {
            task.markAsDone();
            isSuccessful = true;
        }
        return isSuccessful;
    }

    /**
     * Unmarks a particular task in the list.
     *
     * @param taskIndex Index of the task to be unmarked.
     * @return True if able to unmark, false otherwise.
     */
    public boolean unmarkTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex);
        boolean isSuccessful;
        if (task.getStatusIcon() == ' ') {
            isSuccessful = false; // Has not been marked yet
        } else {
            task.markAsUndone();
            isSuccessful = true;
        }
        return isSuccessful;
    }

    /**
     * Adds a new task into the list.
     *
     * @param task New task to be added.
     */
    public void addToList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes an existing task from the list.
     *
     * @param taskIndex Index of the task to be removed.
     */
    public void removeFromList(int taskIndex) {
        this.tasks.remove(taskIndex);
    }

    /**
     * Gets a particular task from the list.
     *
     * @param taskIndex Index of the task to be retrieved.
     * @return The retrieved task.
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     * Gets the details of a particular task.
     *
     * @param task Task to get details of.
     * @return Task details as a single string.
     */
    public String getTaskDetails(Task task) {
        return "[" + task.getTaskType() + "]"
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getExtraInfo();
    }

    /**
     * Gets the details of a particular task.
     *
     * @param taskIndex Index of the task to get details of.
     * @return Task details as a single string.
     */
    public String getTaskDetails(int taskIndex) {
        Task task = this.getTask(taskIndex);
        return this.getTaskDetails(task);
    }

    /**
     * Gets a summary of the total number of tasks.
     *
     * @return Task summary as a single string.
     */
    public String getTasksSummary() {
        int totalTasks = this.numTasks();
        return "Now you have "
                + totalTasks
                + (totalTasks == 1
                ? " task in the list."
                : " tasks in the list.");
    }

    /**
     * Lists the current tasks present, as a single string.
     *
     * @return String representing all current tasks.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();
        int taskNumber = 1;
        for (Task task : this.tasks) {
            result.append(taskNumber).append(". ")
                    .append(this.getTaskDetails(task)).append('\n');
            taskNumber++;
        }

        // If there are existing tasks
        // Remove last newline from result, to prevent duplicate newline when displaying response
        if (taskNumber > 1) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * Checks if description of task matches the keyword.
     *
     * @param description Task description.
     * @param keyword Keyword.
     * @return True if matches, false otherwise.
     */
    private boolean isDescriptionMatching(String description, String keyword) {
        String[] words = description.split(" ");
        for (String word : words) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches the task list for matching tasks.
     *
     * @param keyword Tasks must match this keyword.
     * @return {@code String[]}
     *      , first element is the matching tasks as a string
     *      , second element is the number of matching tasks.
     */
    public String[] findMatchingTasks(String keyword) {
        StringBuilder result = new StringBuilder();
        int taskNumber = 1;

        for (Task task : this.tasks) {
            String description = task.getDescription();
            if (this.isDescriptionMatching(description, keyword)) {
                String taskDetails = this.getTaskDetails(task);
                result.append(taskNumber).append(". ")
                        .append(taskDetails).append('\n');
                taskNumber++;
            }
        }

        // If there are matching tasks
        // Remove last newline from result, to prevent duplicate newline when displaying response
        if (taskNumber > 1) {
            result.deleteCharAt(result.length() - 1);
        }

        // After the iteration, if task number is for example 5
        // Then, there are actually only 4 matching tasks
        return new String[]{result.toString(), Integer.toString(taskNumber - 1)};
    }
}
