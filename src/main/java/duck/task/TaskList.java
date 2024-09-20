package duck.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * duck.task.TaskList class contains the task list.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Constructs a TaskList instance with the given list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void delete(int taskIndex) {
        this.list.remove(taskIndex);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Retrieves a task from the list by index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int taskIndex) {
        return this.list.get(taskIndex);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskIndex The index of the task to be marked.
     */
    public void markTask(int taskIndex) {
        this.getTask(taskIndex).mark();
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public void unmarkTask(int taskIndex) {
        this.getTask(taskIndex).unmark();
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A new TaskList containing tasks with the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> tasksWithKeyword = this.list.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(tasksWithKeyword);
    }

    /**
     * Filters upcoming deadlines for reminder purposes.
     * Only includes deadlines that are due within the next 3 days.
     *
     * @return a list of upcoming Deadline tasks that need reminders
     */
    public TaskList getUpcomingDeadlines() {
        LocalDate today = LocalDate.now();
        int daysAhead = 3;

        ArrayList<Task> upcomingDeadlinesList = this.list.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .filter(deadline -> deadline.isUpcoming(today, daysAhead))
                .collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(upcomingDeadlinesList);
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }
}
