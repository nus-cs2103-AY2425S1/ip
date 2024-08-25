package mortal_reminder.backend;

import mortal_reminder.io.FormattedPrinting;
import mortal_reminder.tasks.Task;

import java.util.ArrayList;
import java.util.Objects;

// The JavaDocs were generated using ChatGPT with minor edits
/**
 * Manages a list of tasks and provides operations to add, delete, retrieve, and clear tasks.
 * <p>
 * The {@code TaskList} class encapsulates an {@link ArrayList} of {@link Task} objects and
 * offers methods to manipulate the list, such as adding new tasks, deleting existing ones,
 * and retrieving tasks by index. It also handles the interaction with storage to add the task to
 * long term storage and formatted printing for printing list to the user.
 */
public class TaskList {
    protected final ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Retrieves the task at the specified index from the list.
     * <p>
     * If the index is out of bounds or the list is empty, an appropriate message is printed
     * and {@code null} is returned.
     *
     * @param index the index of the task to retrieve.
     * @return the {@link Task} at the specified index, or {@code null} if the index is invalid.
     */
    public Task getTask(int index) {
        try {
            return this.taskList.get(index);
        } catch (Exception e) {
            if (this.taskList.isEmpty()) {
                FormattedPrinting.emptyList();
                return null;
            }
            FormattedPrinting.outOfListBounds(this);
            return null;
        }
    }

    /**
     * Adds a task to the list and updates the storage file.
     * <p>
     * If the task's description is not empty, the task is added to the list,
     * appended to the storage file, and a confirmation message is printed.
     * Otherwise, an error message is printed.
     *
     * @param task the {@link Task} to add.
     */
    public void addTask(Task task) {
        if (!Objects.equals(task.getDescription().trim(), "")) {
            Storage.appendToListFile(task);
            this.taskList.add(task);
            FormattedPrinting.addTask(task, this);
        } else {
            FormattedPrinting.descriptionEmptyError();
        }
    }

    /**
     * Loads a task directly into the list from the storage file. Thus, in this method, the task is not added
     * back into the storage file.
     * <p>
     * This method is typically used during the initial loading of tasks from a file.
     *
     * @param task the {@link Task} to load into the list.
     */
    public void loadTask(Task task) {
        this.taskList.add(task);
    }


    /**
     * Deletes a task from the list and updates the storage file.
     * <p>
     * If the task's description is not empty, the task is removed from the list,
     * deleted from the storage file, and a confirmation message is printed.
     * Otherwise, an error message is printed.
     *
     * @param task the {@link Task} to delete.
     */
    public void deleteTask(Task task) {
        if (!Objects.equals(task.getDescription().trim(), "")) {
            this.taskList.remove(task);
            Storage.refreshStorageFile(this);
            FormattedPrinting.deleteTask(task, this);
        } else {
            FormattedPrinting.descriptionEmptyError();
        }
    }

    public TaskList findTasks(String description) {
        TaskList similarTasks = new TaskList();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(description.trim())) {
                similarTasks.loadTask(task);
            }
        }
        return similarTasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Clears all tasks from the list and prints a confirmation message.
     */
    public void clearList() {
        this.taskList.clear();
        FormattedPrinting.clearList();
    }

    /**
     * Returns a string representation of the task list in a format suitable for saving to a file.
     * <p>
     * Each task is converted to its file format and appended to the output string.
     *
     * @return a string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList) {
            output.append(task.convertToFileFormat()).append("\n");
        }
        return output.toString();
    }
}
