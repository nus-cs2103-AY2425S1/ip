package vuewee.task;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
    }

    /**
     * Deserializes a string representation of a TaskList into a TaskList object.
     *
     * @param str the string representation of the TaskList
     * @return the deserialized TaskList object
     */
    public static TaskList deserialize(String str) {
        assert str.length() > 0 : "Serialized string cannot be empty";

        TaskList taskList = new TaskList();
        String[] taskStrings = str.split("\n");
        for (String taskString : taskStrings) {
            if (taskString.trim().isEmpty()) {
                continue;
            }
            String[] parts = taskString.split(Pattern.quote(Task.DELIMETER_SPACE), 2);
            TaskType taskType = TaskType.fromChar(parts[0].charAt(0));
            Task task = taskType.createTask(parts[1]);
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Serializes the TaskList into a string representation for storage.
     *
     * @return the serialized string representation of the TaskList
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            sb.append(task.serialize());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks in the TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index from the TaskList.
     *
     * @param index the index of the tTsk to be retrieved (0-based)
     * @return the Task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    /**
     * Removes the task at the specified index from the TaskList.
     *
     * @param taskNumber the index of the task to be removed (0-based)
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int taskNumber) throws IndexOutOfBoundsException {
        this.tasks.remove(taskNumber);
    }

    /**
     * Marks a task as done or not done.
     *
     * @param taskNumber the index of the task to be marked (0-based)
     * @param isDone     true if the task state changed
     * @return true if the task was successfully marked, false otherwise
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public boolean markTask(int taskNumber, boolean isDone) throws IndexOutOfBoundsException {
        if (taskNumber >= this.tasks.size() || taskNumber < 0) {
            throw new IndexOutOfBoundsException();
        }

        Task task = this.tasks.get(taskNumber);
        if (isDone) {
            boolean success = task.markAsDone();
            return success;
        } else {
            boolean success = task.markAsUndone();
            return success;
        }
    }

    /**
     * Finds tasks that contain the specified search keyword in their description.
     *
     * @param keyword the keyword to search for (case-insensitive)
     * @return ArrayList containing tasks that match the keyword
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        keyword = keyword.toLowerCase();
        for (Task task : this.tasks) {
            if (task.description.toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks that are scheduled for the specified date.
     *
     * @param date the date to search for
     * @return ArrayList containing tasks that are scheduled for the specified date
     */
    public TaskList getTasksOnDate(TaskLocalDate date) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.isWithinDateRange(date)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
