package fred;

import fred.Exceptions.FredException;
import fred.Exceptions.InvalidTaskNumberException;
import fred.Tasks.Deadline;
import fred.Tasks.Event;
import fred.Tasks.Task;
import fred.Tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The TaskList class manages a collection of Task objects. It provides functionality
 * to create tasks, add them to the list, mark them as done or not done, delete them,
 * and load tasks from an external data source.
 */
public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates a new task based on the type and details provided by the user.
     *
     * @param taskType The type of task to create ("todo", "deadline", or "event").
     * @param taskDetails The details of the task, including description and any time-related information.
     * @return The created Task object.
     */
    Task createTask(String taskType, String taskDetails) {
        Task task;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] taskDetailsArr = taskDetails.split(" /", 3);
        String description = taskDetailsArr[0];

        if (taskType.equals("todo")) {
            task = new Todo(description);
        } else if (taskType.equals("deadline")) {
            String byStr = taskDetailsArr[1].substring(3);
            LocalDateTime by = LocalDateTime.parse(byStr, formatter);
            task = new Deadline(description, by);
        } else { // Assuming "event"
            String fromStr = taskDetailsArr[1].substring(5);
            String toStr = taskDetailsArr[2].substring(3);
            LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
            LocalDateTime to = LocalDateTime.parse(toStr, formatter);
            task = new Event(description, from, to);
        }

        return task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    void addToTaskList(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index of the task to be marked as done.
     * @return The task that was marked as done.
     * @throws FredException If the task number is invalid.
     */
    Task markTaskAsDone(int taskNumber) throws FredException {
        try {
            Task task = taskList.get(taskNumber);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNumber The index of the task to be marked as not done.
     * @return The task that was marked as not done.
     * @throws FredException If the task number is invalid.
     */
    Task markTaskAsNotDone(int taskNumber) throws FredException {
        try {
            Task task = taskList.get(taskNumber);
            task.markAsNotDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws FredException If the task number is invalid.
     */
    Task deleteFromTaskList(int taskNumber) throws FredException {
        try {
            Task task = taskList.remove(taskNumber);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Loads tasks into the task list from an external data source.
     *
     * @param tasks The list of tasks to be loaded into the task list.
     */
    void loadTasksFromDataFile(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            addToTaskList(task);
        }
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    String getTaskListString() {
        StringBuilder taskListSb = new StringBuilder();
        int index = 1;
        for (Task task : taskList) {
            taskListSb.append(String.format("%s.%s", index++, task));
            taskListSb.append("\n");
        }
        return taskListSb.toString();
    }

    String findTasksInTaskList(String keyword) {
        StringBuilder tasksWithKeyword = new StringBuilder();
        for (Task task : taskList) {
            if (task.checkForKeyword(keyword)) {
                tasksWithKeyword.append(task.toString());
                tasksWithKeyword.append("\n");
            }
        }
        return tasksWithKeyword.toString();
    }
}
