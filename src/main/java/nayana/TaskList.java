package nayana;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import nayana.task.Deadline;
import nayana.task.Event;
import nayana.task.Task;

/**
 * Represents a list of tasks.
 * This class manages a collection of tasks and provides methods to add, remove, and list them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with an initial set of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task delete(int index) throws NayanaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NayanaException("Invalid task index.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The updated task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task markAsDone(int index) throws NayanaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NayanaException("Invalid task index.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The updated task.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task markAsNotDone(int index) throws NayanaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NayanaException("Invalid task index.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns a list of all tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a string representation of the task list.
     * Each task is represented with an index and a description.
     *
     * @return A string listing all tasks with their indices.
     */
    @Override
    public String toString() {
        StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOfTasks.toString();
    }
    /**
     * Finds and returns a list of tasks that contain the specified search query in their description.
     *
     * @param findValue The search query to look for in task descriptions.
     * @return An ArrayList of tasks that match the search query.
     */
    public ArrayList<Task> findTasks(String findValue) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().contains(findValue)) {
                foundTasks.add(currTask);
            }
        }
        return foundTasks;
    }

    /**
     * Retrieves a list of upcoming tasks that are either deadlines or events.
     * A task is considered upcoming if it is due or ends within the next 7 days.
     *
     * @return An {@code ArrayList<Task>} containing the upcoming tasks.
     */
    public ArrayList<Task>  getUpcomingTasks() {
        ArrayList<Task> upcomingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (Objects.equals(currTask.getType(), "D ")) {
                if (isUpcomingDeadline((Deadline)currTask)) {
                    upcomingTasks.add(currTask);
                }
            } else if (Objects.equals(currTask.getType(), "E ")) {
                if (isUpcomingEvent((Event)currTask)) {
                    upcomingTasks.add(currTask);
                }
            }
        }
        return upcomingTasks;
    }

    /**
     * Determines if the given event is upcoming.
     * An event is considered upcoming if its end date is within the next 7 days.
     *
     * @param task The {@code Event} to check.
     * @return {@code true} if the event is upcoming, {@code false} otherwise.
     */
    private boolean isUpcomingEvent(Event task) {
        LocalDate today = LocalDate.now();
        return !task.getEndDate().isAfter(today.plusDays(7));
    }

    /**
     * Determines if the given deadline is upcoming.
     * A deadline is considered upcoming if its date is within the next 7 days,
     * and not in the past.
     *
     * @param task The {@code Deadline} to check.
     * @return {@code true} if the deadline is upcoming, {@code false} otherwise.
     */
    private boolean isUpcomingDeadline(Deadline task) {
        LocalDate today = LocalDate.now();
        return !task.getDate().isBefore(today) &&
              !task.getDate().isAfter(today.plusDays(7));
    }
}
