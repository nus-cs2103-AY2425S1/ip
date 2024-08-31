package bobby.tasklist;

import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws InvalidTaskNumberException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }

    }

    public Task remove(int index) throws InvalidTaskNumberException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that are associated with a specific date.
     * This includes tasks with deadlines on the date and events happening on the date.
     *
     * @param date The date to search for in tasks.
     * @return An ArrayList of tasks that match the date.
     */
    public ArrayList<Task> findTasksByDate(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                matchingTasks.add(task);
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().equals(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
