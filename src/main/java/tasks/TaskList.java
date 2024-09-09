package tasks;

import exceptions.JarException;
import storage.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * The TaskList class manages the addition, removal, and modification of tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Constructs a TaskList by loading tasks from the provided storage.
     *
     * @param storage The storage from which tasks are loaded.
     * @throws IOException  If an I/O error occurs while loading tasks.
     * @throws JarException If the data in the storage is corrupted.
     */
    public TaskList(Storage storage) throws IOException, JarException {
        this.tasks = new ArrayList<>(storage.load());  // Load tasks from the storage
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     * @throws JarException If the task is null.
     */
    public void addTask(Task task) throws JarException {
        if (task == null) {
            throw new JarException("Cannot add a null task.");
        }
        this.tasks.add(task);
    }

    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return A string listing all tasks, or a message indicating that there are no tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No Tasks added yet, pls add tasks!";
        }
        StringBuilder taskListString = new StringBuilder();
        int counter = 1;
        for (Task task : tasks) {
            taskListString.append(counter).append(". ").append(task.toString()).append("\n");
            counter++;
        }
        return taskListString.toString();
    }

    /**
     * Marks a task as completed based on its index in the TaskList.
     *
     * @param index The index of the task to be marked as completed.
     * @throws JarException If the index is invalid.
     */
    public void markTaskAsDone(int index) throws JarException {
        Task task = getTask(index);
        task.setStatus(true);
    }

    /**
     * Marks a task as not completed based on its index in the TaskList.
     *
     * @param index The index of the task to be marked as not completed.
     * @throws JarException If the index is invalid.
     */
    public void markTaskAsUndone(int index) throws JarException {
        Task task = getTask(index);
        task.setStatus(false);
    }

    /**
     * Retrieves a task from the TaskList based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws JarException If the index is invalid.
     */
    public Task getTask(int index) throws JarException {
        if (index < 0 || index >= tasks.size()) {
            throw new JarException("Invalid task number!");
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param index The index of the task to be deleted.
     * @throws JarException If the index is invalid.
     */
    public void deleteTask(int index) throws JarException {
        if (index < 0 || index >= tasks.size()) {
            throw new JarException("Invalid task number, cannot delete task");
        }
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     * <p>
     * This method searches through the list of tasks and collects all tasks whose string representation
     * contains the provided keyword. If the keyword is null, empty, or only whitespace, a {@link JarException}
     * is thrown to indicate that the search criteria is invalid.
     * </p>
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return An {@link ArrayList} of {@link Task} objects that match the keyword.
     * @throws JarException If the keyword is null, empty, or only contains whitespace.
     */
    public ArrayList<Task> findTasks(String keyword) throws JarException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new JarException("Task cannot be empty!");
        } else {
            ArrayList<Task> sameTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.toString().contains(keyword)) {
                    sameTasks.add(task);
                }
            }
            return sameTasks;
        }
    }

    public boolean findDuplicate(Task task, TaskList taskList) {
        for (Task currTask: taskList.getTasks()) {
            String todoDescription = task.getTaskContent();
            if (task.getTaskType().equals("todo")) {
                if (todoDescription.equals(currTask.getTaskContent())) {
                    return true;
                }
            } else if (task.getTaskType().equals("event")) {
                Event taskTemp = (Event) task;
                String taskFrom = taskTemp.getFrom();
                String taskTo = taskTemp.getTo();
                if (currTask.getTaskType().equals("event")) {
                    Event currTaskTemp = (Event) currTask;
                    String currTaskFrom = currTaskTemp.getFrom();
                    String currTaskTo = currTaskTemp.getTo();
                    boolean sameDates = taskFrom.equals(currTaskFrom) && taskTo.equals(currTaskTo);
                    if (todoDescription.equals(currTask.getTaskContent()) && sameDates) {
                        return true;
                    }
                }
            } else {
                DeadLine taskTemp = (DeadLine) task;
                LocalDateTime taskBy = taskTemp.getBy();
                if (currTask.getTaskType().equals("deadline")) {
                    DeadLine currTaskTemp = (DeadLine) currTask;
                    LocalDateTime currTaskBy = currTaskTemp.getBy();
                    boolean sameDate = taskBy.equals(currTaskBy);
                    if (todoDescription.equals(currTask.getTaskContent()) && sameDate) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

}
