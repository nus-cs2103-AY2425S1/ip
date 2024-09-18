package meow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Manages a list of tasks. This class provides methods to add, delete,
 * retrieve, and get information about tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructs a TaskList with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws MeowException If the index is out of bounds.
     */
    public void deleteTask(int index) throws MeowException {
        assert index >= 0 && index < taskCount : "Task index out of bounds: " + index;

        // if block is for double protection
        if (index >= 0 && index < taskCount) {
            tasks.remove(index);
            taskCount--;
        } else {
            throw new MeowException("Invalid task number.");
        }
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws MeowException If the index is out of bounds.
     */
    public Task getTask(int index) throws MeowException {
        assert index >= 0 && index < taskCount : "Task index out of bounds: " + index;

        // if block is for double protection
        if (index >= 0 && index < taskCount) {
            return tasks.get(index);
        } else {
            throw new MeowException("Invalid task number.");
        }
    }

    /**
     * @return The number of tasks in list.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns full task list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskList sort() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1 instanceof ToDo && t2 instanceof ToDo) {
                    return 0; // Both are Todos, keep their order as-is
                } else if (t1 instanceof ToDo) {
                    return 1; // Todos come after Deadline or Event
                } else if (t2 instanceof ToDo) {
                    return -1; // t1 is Deadline or Event, which comes before Todo
                }

                // If both are Deadlines or Events, compare based on their dates
                if (t1 instanceof Deadline && t2 instanceof Deadline) {
                    return ((Deadline) t1).getBy().compareTo(((Deadline) t2).getBy());
                } else if (t1 instanceof Event && t2 instanceof Event) {
                    return ((Event) t1).getFrom().compareTo(((Event) t2).getFrom());
                }

                // If one is a Deadline and the other is an Event, compare their dates
                if (t1 instanceof Deadline && t2 instanceof Event) {
                    return ((Deadline) t1).getBy().compareTo(((Event) t2).getFrom());
                } else if (t1 instanceof Event && t2 instanceof Deadline) {
                    return ((Event) t1).getFrom().compareTo(((Deadline) t2).getBy());
                }

                return 0;
            }
        });
        return this;
    }

    /**
     * Converts the task list into a numbered string format, with each task on a new line.
     *
     * @return A string representing the list of tasks.
     * @throws MeowException If there is an error accessing the tasks.
     */
    public String stringify() throws MeowException {
        StringBuilder listMessage = new StringBuilder();
        for (int i = 0; i < this.getTaskCount(); i++) {
            if (i != this.getTaskCount() - 1) {
                listMessage.append(i + 1).append(". ").append(this.getTask(i)).append("\n");
            } else {
                listMessage.append(i + 1).append(". ").append(this.getTask(i));
            }
        }
        return listMessage.toString();
    }

    /**
     * Generates a message confirming that a task has been added to the task list.
     *
     * @return A message indicating the task was added, including the current task count.
     * @throws MeowException If there is an error accessing the tasks.
     */
    public String addTaskMessage() throws MeowException {
        return "Got it. I've added this task:\n"
                + this.getTask(this.getTaskCount() - 1) + "\n"
                + (this.getTaskCount() <= 1
                ? "Now you have " + this.getTaskCount() + " task in the list."
                : "Now you have " + this.getTaskCount() + " tasks in the list.");
    }

    /**
     * Generates a message confirming that a task has been deleted from the task list.
     *
     * @param task The task that has been deleted.
     * @return A message indicating the task was deleted, including the current task count.
     * @throws MeowException If there is an error accessing the tasks.
     */
    public String deleteTaskMessage(Task task) throws MeowException {
        return "Noted. I've removed this task:\n" + task + "\n"
                + (this.getTaskCount() <= 1
                ? "Now you have " + this.getTaskCount() + " task in the list."
                : "Now you have " + this.getTaskCount() + " tasks in the list.");
    }
}
