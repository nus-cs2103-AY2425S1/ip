package fred;

import fred.Exceptions.FredException;
import fred.Exceptions.InvalidDeadlineException;
import fred.Exceptions.InvalidEventException;
import fred.Exceptions.InvalidTaskNumberException;
import fred.Tasks.Deadline;
import fred.Tasks.Event;
import fred.Tasks.Task;
import fred.Tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The TaskList class manages a collection of Task objects. It provides functionality
 * to create tasks, add them to the list, mark them as done or not done, delete them,
 * and load tasks from an external data source.
 */
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a new task based on the type and details provided by the user.
     *
     * @param taskType The type of task to create ("todo", "deadline", or "event").
     * @param taskDetails The details of the task, including description and any time-related information.
     * @return The created Task object.
     */
    Task createTask(TaskType taskType, String taskDetails) throws FredException {
        Task task = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] taskDetailsArr = taskDetails.split(" /", 3);
        String description = taskDetailsArr[0];

        if (taskType == TaskType.TODO) {
            task = new Todo(description);
        } else if (taskType == TaskType.DEADLINE) {
            String byStr = taskDetailsArr[1];
            if (!byStr.startsWith("by ")) {
                throw new InvalidDeadlineException();
            }
            byStr = byStr.substring(3);
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(byStr, formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineException();
            }
            task = new Deadline(description, by);
        } else if (taskType == TaskType.EVENT) {
            String fromStr = taskDetailsArr[1];
            String toStr = taskDetailsArr[2];
            if (!fromStr.startsWith("from ")) {
                throw new InvalidEventException();
            }
            if (!toStr.startsWith("to ")) {
                throw new InvalidEventException();
            }
            fromStr = fromStr.substring(5);
            toStr = toStr.substring(3);
            LocalDateTime from;
            LocalDateTime to;
            try {
                from = LocalDateTime.parse(fromStr, formatter);
                to = LocalDateTime.parse(toStr, formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidEventException();
            }
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
        tasks.add(task);
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
            Task task = tasks.get(taskNumber);
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
            Task task = tasks.get(taskNumber);
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
            Task task = tasks.remove(taskNumber);
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
        return tasks.size();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    String getTasksAsString() {
        StringBuilder taskListSb = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            taskListSb.append(String.format("%s.%s", index++, task));
            taskListSb.append("\n");
        }
        return taskListSb.toString();
    }

    String findTasksInTaskList(String keyword) {
        StringBuilder tasksWithKeyword = new StringBuilder();
        for (Task task : tasks) {
            if (task.checkForKeyword(keyword)) {
                tasksWithKeyword.append(task);
                tasksWithKeyword.append("\n");
            }
        }
        return tasksWithKeyword.toString();
    }

    Task addTagToTask(int taskNumber, String tag) throws FredException {
        try {
            Task task = tasks.get(taskNumber);
            task.addTag(tag);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }
}

