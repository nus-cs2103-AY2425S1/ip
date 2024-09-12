package task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * List of tasks stored by BotManager.
 *
 * @author dwsc37
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Add a <code>task</code> to the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark a <code>task</code> in the task list as done.
     *
     * @param taskIndex Index of the task to be marked.
     * @return Task marked as done.
     */
    public Task markTask(int taskIndex) {
        assert(taskIndex >= 0 && taskIndex < tasks.size());
        tasks.get(taskIndex).mark();
        return tasks.get(taskIndex);
    }

    /**
     * Mark a <code>task</code> in the task list as undone.
     *
     * @param taskIndex Index of the task to be unmarked.
     * @return Task marked as undone.
     */
    public Task unmarkTask(int taskIndex) {
        assert(taskIndex >= 0 && taskIndex < tasks.size());
        tasks.get(taskIndex).unmark();
        return tasks.get(taskIndex);
    }

    /**
     * Delete a <code>task</code> in the task list.
     *
     * @param taskIndex Index of the task to be deleted.
     * @return Task deleted.
     */
    public Task deleteTask(int taskIndex) {
        assert(taskIndex >= 0 && taskIndex < tasks.size());
        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns a summary of the tasks in the task list.
     *
     * @return Summary of tasks in the task list.
     */
    public String getSummary() {
        if (tasks.isEmpty()) {
            return "You have no tasks currently.";
        } else if (getTaskCount() == 1) {
            return "You have 1 task currently.";
        }
        return "You have " + getTaskCount() + " tasks currently.";
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getSummary()).append("\n");
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(i + 1).append(". ");
            stringBuilder.append(tasks.get(i).toString()).append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Returns the data representation of the task list, for writing to the data file.
     *
     * @return Data representation of the task list.
     */
    public String toData() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getTaskCount(); i++) {
            stringBuilder.append(tasks.get(i).toData());
            if (i != getTaskCount() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a string all tasks in the the task list which match the given search string.
     *
     * @param  searchString Search string to compare.
     * @return String of all tasks matching the given search string.
     */
    public String findMatches(String searchString) {
        StringBuilder stringBuilder = new StringBuilder();
        int matchCount = 0;
        for (int i = 0; i < getTaskCount(); i++) {
            if (tasks.get(i).isMatch(searchString)) {
                matchCount++;
                stringBuilder.append(i + 1).append(". ");
                stringBuilder.append(tasks.get(i).toString()).append("\n");
            }
        }

        if (matchCount == 1) {
            stringBuilder.insert(0, "1 matching task found!\n");
        } else {
            stringBuilder.insert(0, matchCount + " tasks found!\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Generate the schedule for the task list on a given date.
     * @param date Date to generate the schedule for.
     * @return List of tasks in the schedule.
     */
    public String generateSchedule(LocalDate date) {
        StringBuilder stringBuilder = new StringBuilder();
        int scheduleCount = 0;
        for (int i = 0; i < getTaskCount(); i++) {
            if (tasks.get(i).isOnDate(date)) {
                scheduleCount++;
                stringBuilder.append(i + 1).append(". ");
                stringBuilder.append(tasks.get(i).toString()).append("\n");
            }
        }

        if (scheduleCount == 1) {
            stringBuilder.insert(0, "1 task on " + date + "!\n");
        } else {
            stringBuilder.insert(0, scheduleCount + " tasks on " + date + "!\n");
        }

        return stringBuilder.toString();
    }
}
