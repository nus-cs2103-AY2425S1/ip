package echo.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a list of tasks,
 * including adding, removing, marking, unmarking, and retrieving tasks.
 */
public class TaskList {
    private List<Task> tasks;
    /**
     * Constructs a TaskList with an empty List<Task>.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }
    /**
     * Adds a new task to tasks based on the provided description, TaskType, and additional information.
     *
     * @param description the description of the task
     * @param type the type of the task (TaskType.TODO, TaskType.EVENT)
     * @param info additional information related to the task (e.g., start and end dates for an event)
     */
    public void addTask(String description, TaskType type, String info) {
        assert !description.isEmpty(): "Description shouldn't be empty";
        assert type != null : "Task type shouldn't be null";

        switch (type) {
        case TODO:
            tasks.add(new Todo(description));
            break;
        case EVENT:
            String[] parts = info.split("->");
            tasks.add(new Event(description, parts[0], parts[1]));
            break;
        }
    }
    /**
     * Searches for tasks in the task list that contain the specified substring
     * and returns them as a formatted string for printing.
     *
     * @param stringToFind The substring to search for within the tasks.
     * @return A formatted string containing the list of tasks that contain
     *         the specified substring, each prefixed with its position in the list.
     *         If no tasks contain the substring, an empty string is returned.
     */
    public String getFoundTasks(String stringToFind) {
        String foundTasks = "";
        int count = 1;
        for (Task task: tasks) {
            String taskString = task.getTaskString();
            if (taskString.contains(stringToFind)) {
                foundTasks += count + ". " + taskString + "\n";
                count++;
            }
        }
        return foundTasks;
    }
    /**
     * Returns a formatted string representing the tasks in the list,
     * intended for saving to a file.
     *
     * @return a string representing the tasks in the list
     */
    public String getTasksToSave() {
        String s = "";
        for (Task t : tasks) {
            s += t.getData() + "\n";
        }
        return s;
    }
    /**
     * Adds a Deadline task to tasks with the given description and deadline date.
     *
     * @param description the description of the deadline task
     * @param deadline the deadline date of the task
     */
    public void addDeadline(String description, LocalDate deadline) {
        assert !description.isEmpty(): "Deadline description should not be empty";
        assert  deadline != null: "Deadline should not be null";

        tasks.add(new Deadline(description, deadline));
    }
    /**
     * Returns a formatted string representing all the tasks in the list.
     * Intended for printing by the Ui class.
     *
     * @return a string representing all the tasks in the list
     */
    public String getTasksString() {
        String tasksString = "";
        int count = 1;
        for (Task task : tasks) {
            assert !task.getTaskString().isEmpty(): "Task string should not be empty";
            tasksString += count + ". " + task.getTaskString() + "\n";
            count++;
        }
        return tasksString;
    }
    /**
     * Marks the task at the specified index of tasks as complete.
     *
     * @param index the 1-based index of the task to be marked
     */
    public void markTask(int index) {
        assert index >= 0: "Index should not be out of range";
        tasks.get(index - 1).completeTask();
    }
    /**
     * Returns a formatted string representing the task at the specified index.
     *
     * @param index the 1-based index of the task
     * @return a string representing the task at the specified index of tasks
     */
    public String getTaskString(int index) {
        assert index > 0: "Index should not be out of range";
        return tasks.get(index - 1).getTaskString() + "\n";
    }
    /**
     * Unmarks the task at the specified index of tasks, indicating it is not complete.
     *
     * @param index the 1-based index of the task to be unmarked
     */
    public void unmarkTask(int index) {
        assert index > 0: "Index should not be out of range";
        tasks.get(index - 1).uncompleteTask();
    }
    /**
     * Returns the number of tasks currently in the list.
     *
     * @return the number of tasks in the list
     */
    public int getNumTasks() {
        assert tasks != null: "Tasks should not be null";
        return tasks.size();
    }
    /**
     * Deletes the task at the specified index from tasks.
     *
     * @param index the 1-based index of the task to be deleted
     */
    public void deleteTask(int index) {
        assert index > 0: "Index should not be out of range";
        tasks.remove(index - 1);
    }
    public TaskType getTaskType(int index) {
        return tasks.get(index - 1).getTaskType();
    }
    public void updateTask(int index, Task task) {
        if (tasks.get(index - 1).getIsComplete()) {
            task.completeTask();
        }
        tasks.set(index - 1, task);
    }

    public String[] getTempTaskStrings(int index) {
        return tasks.get(index - 1).getTempStrings();
    }
}
