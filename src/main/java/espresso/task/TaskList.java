package espresso.task;
import java.util.ArrayList;
import espresso.command.InvalidCommandException;

/**
 * Represents a list of tasks.
 * It Manages adding, removing, retrieving tasks, and provides other helpful methods
 * that can help make use of the task list.
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
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @return The added task.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Removes a task from the TaskList by using provided index.
     *
     * @param index The index of the task to be removed.
     * @throws InvalidCommandException If the index is out of range.
     */
    public void removeTask(int index) throws InvalidCommandException{
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    /**
     * Retrieves a task from the TaskList by using provided index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws InvalidCommandException If the index is out of range.
     */
    public Task getTask(int index) throws InvalidCommandException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new InvalidCommandException("Invalid task index.");
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    /* Finds all tasks that contain the specified substring.
     *
     * @param substring the substring to search for.
     * @return the list of tasks that contain the substring.
     */
    public TaskList find(String str) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.contains(str)) {
                matchingTasks.add(task);
            }
        }
        TaskList res = new TaskList(matchingTasks);
        return res;
    }

    public boolean detectAnomaly(Task newTask) {
        for (Task task : tasks) {
            if (task instanceof EventTask && newTask instanceof EventTask) {
                EventTask existingTask = (EventTask) task;
                EventTask eventTask = (EventTask) newTask;

                // Check for overlapping event times
                if (eventTask.getEnds().after(existingTask.getStarts()) && eventTask.getStarts().before(existingTask.getEnds())) {
                    return true; // Conflict found
                }

            } else if (task instanceof DeadlineTask && newTask instanceof DeadlineTask) {
                DeadlineTask existingTask = (DeadlineTask) task;
                DeadlineTask deadlineTask = (DeadlineTask) newTask;

                // Check if two DeadlineTasks have the same date
                if (existingTask.getDeadline().equals(deadlineTask.getDeadline())) {
                    return true; // Conflict found
                }

            } else if (task instanceof EventTask && newTask instanceof DeadlineTask) {
                EventTask eventTask = (EventTask) task;
                DeadlineTask deadlineTask = (DeadlineTask) newTask;

                // Check if the deadline falls within the event's time range
                if (deadlineTask.getDeadline().after(eventTask.getStarts()) && deadlineTask.getDeadline().before(eventTask.getEnds())) {
                    return true; // Conflict found
                }

            } else if (task instanceof DeadlineTask && newTask instanceof EventTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                EventTask eventTask = (EventTask) newTask;

                // Check if the deadline falls within the event's time range
                if (deadlineTask.getDeadline().after(eventTask.getStarts()) && deadlineTask.getDeadline().before(eventTask.getEnds())) {
                    return true; // Conflict found
                }
            }
        }
        return false; // No conflict
    }

    public Task addTaskWithAnomalyCheck(Task task) throws InvalidCommandException {
        if (detectAnomaly(task)) {
            throw new InvalidCommandException("Scheduling conflict detected.");
        }
        return addTask(task);
    }

    /**
     * Returns a string representation of the TaskList, where each task is preceded
     * by its position in the list.
     *
     * @return A string representation of the TaskList.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += i + 1 + "." + tasks.get(i);
            if (i < tasks.size() - 1) {
                res += "\n";
            }
        }
        return res;
    }
}