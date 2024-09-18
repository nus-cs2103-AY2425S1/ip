package chatbaby;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks, providing methods to manage and manipulate the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTaskAt(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the TaskList.
     *
     * @param index The index of the task to delete.
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
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
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if the TaskList is empty, false otherwise.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean hasTask() {
        return !tasks.isEmpty();
    }

    /**
     * Sorts the task list in the TaskList.
     */
    public void sortTasks() {
        tasks.sort((task1, task2) -> {
            // Check if both tasks have deadlines (assume Deadline/Event have getDateTime())
            if (task1 instanceof Deadline && task2 instanceof Event) {
                return ((Deadline) task1).getDeadline()
                        .compareTo(((Event) task2).getEventEndTime());
            } else if (task1 instanceof Event && task2 instanceof Deadline) {
                return ((Event) task1).getEventEndTime()
                        .compareTo(((Deadline) task2).getDeadline());
            } else if (task1 instanceof Deadline && task2 instanceof Deadline) {
                return ((Deadline) task1).getDeadline().compareTo(((Deadline) task2).getDeadline());
            } else if (task1 instanceof Deadline) {
                // Task1 has deadline, task2 doesn't
                return -1; // Task1 should come before
            } else if (task2 instanceof Deadline) {
                // Task2 has deadline, task1 doesn't
                return 1; // Task2 should come before
            } else if (task1 instanceof Event && task2 instanceof Event) {
                return ((Event) task1).getEventEndTime().compareTo(((Event) task2).getEventEndTime());
            } else if (task1 instanceof Event) {
                return -1;
            } else if (task2 instanceof Event) {
                return 1;
            } else {
                // If both are Todos or neither has a deadline, sort alphabetically by description
                return task1.getName().compareTo(task2.getName());
            }
        });
    }

    /**
     * Prints the list of tasks to the console.
     */
    public void listTasks() {
        this.sortTasks();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints the tasks that end on the specified date to the console.
     *
     * @param date The date to filter tasks by.
     * @return True if there are tasks ending on the specified date, false otherwise.
     */
    public boolean listTasksOn(LocalDate date) {
        this.sortTasks();
        boolean hasTask = false;
        System.out.println("Tasks that end on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        for (Task task : tasks) {
            if (task.isOnDate(date)) {
                System.out.println(task);
                hasTask = true;
            }
        }
        return hasTask;
    }
}
