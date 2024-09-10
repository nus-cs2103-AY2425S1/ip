package dgpt.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks in the Dgpt application.
 * <p>
 * The {@code TaskList} class provides methods for adding, marking, unmarking, deleting, and retrieving tasks.
 * Tasks can be of different types, including {@code ToDo}, {@code Deadline}, and {@code Event}.
 * </p>
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param taskList The initial list of tasks to set in the {@code TaskList}.
     */
    public TaskList(List<Task> taskList) {
        assert taskList != null : "taskList should not be null";
        this.taskList = taskList;
    }

    /**
     * Adds a new {@code ToDo} task to the list.
     *
     * @param description The description of the {@code ToDo} task.
     * @return The newly created {@code ToDo} task.
     */
    public Task addToDoToList(String description) {
        assert description != null && !description.isBlank() : "description cannot be null or empty";
        Task newTask = new ToDo(description);
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds a new {@code Deadline} task to the list.
     *
     * @param description The description of the {@code Deadline} task.
     * @param deadline    The deadline for the {@code Deadline} task, in a dd/MM/yyyy format.
     * @return The newly created {@code Deadline} task.
     * @throws DateTimeParseException If the provided deadline is in an invalid format.
     */
    public Task addDeadlineToList(String description, String deadline) throws DateTimeParseException {
        assert description != null && !description.isBlank() : "description cannot be null or empty";
        Task newTask = new Deadline(description, deadline);
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Adds a new {@code Event} task to the list.
     *
     * @param description The description of the {@code Event} task.
     * @param startTime   The start time of the {@code Event} task, in a dd/MM/yyyy HHmm format.
     * @param endTime     The end time of the {@code Event} task, in a dd/MM/yyyy HHmm format.
     * @return The newly created {@code Event} task.
     */
    public Task addEventToList(String description, String startTime, String endTime) {
        assert description != null && !description.isBlank() : "description cannot be null or empty";
        Task newTask = new Event(description, startTime, endTime);
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be mark as done.
     * @return The task that was marked as done.
     */
    public Task markTask(int index) {
        assert index >= 0 && index < this.taskList.size() : "Index out of bounds";
        this.taskList.get(index).mark();
        return this.taskList.get(index);
    }

    /**
     * Unmarks a task at the specified index as not done.
     *
     * @param index The index of the task to unmark.
     * @return The task that was unmarked.
     */
    public Task unmarkTask(int index) {
        assert index >= 0 && index < this.taskList.size() : "Index out of bounds";
        this.taskList.get(index).unmark();
        return this.taskList.get(index);
    }

    /**
     * Deletes a task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < this.taskList.size() : "Index out of bounds";
        Task curr = this.taskList.get(index);
        this.taskList.remove(index);
        return curr;
    }

    /**
     * Returns the list of all tasks in the {@code TaskList}.
     *
     * @return A {@code List} of {@code Task} objects in the {@code TaskList}.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the number of tasks in the {@code TaskList}.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Searches for tasks in the list that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A list of tasks that contain the keyword in their description.
     */
    public List<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.isBlank() : "keyword cannot be null or empty";
        List<Task> res = new ArrayList<>();
        for (Task t : this.taskList) {
            if (t.getDescription().contains(keyword)) {
                res.add(t);
            }
        }
        return res;
    }
}
