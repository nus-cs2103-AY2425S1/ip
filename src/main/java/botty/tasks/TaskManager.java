package botty.tasks;

import java.util.ArrayList;

import botty.exceptions.TaskListEmptyException;
import botty.exceptions.TaskNumberNotFoundException;
import botty.exceptions.TasksNotFoundException;

/**
 * Manages all interactions with the task list
 */
public class TaskManager {
    // The list of tasks
    private final ArrayList<Task> taskList = new ArrayList<>(100);

    /**
     * Returns a string representation of the list of tasks
     * @throws TaskListEmptyException if the task list is empty
     */
    public String list() throws TaskListEmptyException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskList.size() - 1; i++) {
            content.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        content.append(taskList.size()).append(". ").append(taskList.get(taskList.size() - 1));
        return content.toString();
    }

    /**
     * Marks the task at the given index as done
     * @param index the index of the task
     * @return the marked task
     * @throws TaskListEmptyException if the task list is empty
     * @throws TaskNumberNotFoundException if the task number is out of range
     */
    public Task markTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        Task task = taskList.get(index);
        task.setCompleted(true);
        return task;
    }
    /**
     * Unmarks the task at the given index as done
     * @param index the index of the task
     * @return the unmarked task
     * @throws TaskListEmptyException if the task list is empty
     * @throws TaskNumberNotFoundException if the task number is out of range
     */
    public Task unmarkTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        Task task = taskList.get(index);
        task.setCompleted(false);
        return task;
    }
    /**
     * Deletes the task at the given index
     * @param index the index of the task
     * @return the deleted task
     * @throws TaskListEmptyException if the task list is empty
     * @throws TaskNumberNotFoundException if the index is out of range
     */
    public Task deleteTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        return task;
    }
    /**
     * Adds the task to the task list
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the current number of tasks in the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the given index
     * @param index the index of the task to retrieve
     * @return the retrieved task
     * @throws TaskListEmptyException if the task list is empty
     * @throws TaskNumberNotFoundException if the index is out of range
     */
    public Task getTask(int index) throws TaskListEmptyException, TaskNumberNotFoundException {
        if (size() == 0) {
            throw new TaskListEmptyException();
        }
        if (index < 0 || index > size() - 1) {
            throw new TaskNumberNotFoundException(index + 1, size());
        }
        return taskList.get(index);
    }

    /**
     * Returns a list of tasks that matches the given keyword
     * @param keyword the keyword to be matched
     * @return the list of tasks matched as a string
     * @throws TaskListEmptyException when the task list is empty
     * @throws TasksNotFoundException when no tasks are found matching the keyword
     */
    public String findTasks(String keyword) throws TaskListEmptyException, TasksNotFoundException {
        if (taskList.isEmpty()) {
            throw new TaskListEmptyException();
        }
        ArrayList<String> matchedTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().toLowerCase().matches(".*\\b" + keyword.toLowerCase() + "\\b.*")) {
                matchedTasks.add((i + 1) + ". " + taskList.get(i).toString());
            }
        }
        if (matchedTasks.isEmpty()) {
            throw new TasksNotFoundException();
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matchedTasks.size() - 1; i++) {
            result.append(matchedTasks.get(i)).append("\n");
        }
        result.append(matchedTasks.get(matchedTasks.size() - 1));
        return result.toString();
    }
}
